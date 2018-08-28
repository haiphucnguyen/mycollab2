package com.mycollab.runner;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.security.CodeSource;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author MyCollab Ltd
 * @since 5.0.7
 */
public class Executor {
    private static Logger LOG = LoggerFactory.getLogger(Executor.class);

    private static String PID_FILE = ".mycollab.pid";

    private static void unpackFile(File upgradeFile) throws IOException {
        if (isValidZipFile(upgradeFile)) {
            File libFolder = new File(getUserDir(), "lib");
            File i18nFolder = new File(getUserDir(), "i18n");
            assertFolderWritePermission(libFolder);
            assertFolderWritePermission(i18nFolder);

            //Hack for windows since the jar files still be keep by process, we will wait until
            // the process is ended actually
            int tryTimes = 0;
            while (tryTimes < 10) {
                try {
                    FileUtils.deleteDirectory(libFolder);
                    FileUtils.deleteDirectory(i18nFolder);
                    break;
                } catch (Exception e) {
                    tryTimes++;
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            byte[] buffer = new byte[2048];

            try (ZipInputStream inputStream = new ZipInputStream(new FileInputStream(upgradeFile))) {
                ZipEntry entry;
                while ((entry = inputStream.getNextEntry()) != null) {
                    if (!entry.isDirectory() && (entry.getName().startsWith("lib/") || entry.getName().startsWith("i18n"))) {
                        File candidateFile = new File(getUserDir(), entry.getName());
                        candidateFile.getParentFile().mkdirs();
                        LOG.info("Copy file: " + entry.getName());
                        try (FileOutputStream output = new FileOutputStream(candidateFile)) {
                            int len;
                            while ((len = inputStream.read(buffer)) > 0) {
                                output.write(buffer, 0, len);
                            }
                        }
                    }
                }
            }
        } else {
            throw new RuntimeException("Invalid installer file. It is not a zip file");
        }
    }

    private static boolean isValidZipFile(final File file) {
        try (ZipFile zipFile = new ZipFile(file)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void assertFolderWritePermission(File folder) throws IOException {
        if (!folder.canWrite()) {
            throw new IOException(System.getProperty("user.name") + " does not have write permission on folder " + folder.getAbsolutePath()
                    + ". The upgrade could not be proceeded. Please correct permission before you upgrade MyCollab again");
        }
    }


    private Executor() {
        File jarFile = getUserDir();
        System.setProperty("MYCOLLAB_APP_HOME", jarFile.getAbsolutePath());
    }

    private void runServer(String[] args) {
        LOG.info("Start MyCollab server process");
        final AppProcess process = new AppProcess(args);
        File pIdFile = new File(getUserDir(), PID_FILE);
        try (FileWriter writer = new FileWriter(new File(getUserDir(), PID_FILE), false)) {
            writer.write("START");
        } catch (Exception e) {
            LOG.error("Error to write pid file", e);
            System.exit(-1);
        }

        new Thread(() -> {
            Path toWatch = Paths.get(pIdFile.getParent());
            try (final WatchService pIdWatcher = FileSystems.getDefault().newWatchService()) {
                toWatch.register(pIdWatcher, StandardWatchEventKinds.ENTRY_MODIFY);
                long lastInvokeTime = System.currentTimeMillis();
                String lastFileContent = "";
                // get the first event before looping
                while (true) {
                    final WatchKey key = pIdWatcher.take();
                    LOG.info("Watch key: " + key);
                    // we have a polled event, now we traverse it and
                    // receive all the states from it
                    for (WatchEvent event : key.pollEvents()) {
                        final WatchEvent.Kind<?> kind = event.kind();
                        // A new Path was created
                        Path modifiedPath = ((WatchEvent<Path>) event).context();
                        // Output
                        String fileName = modifiedPath.toFile().getName();
                        // Overflow event
                        if (StandardWatchEventKinds.ENTRY_MODIFY == kind && PID_FILE.equals(fileName)) {
                            String fileContent = FileUtils.readFileToString(pIdFile);
                            long currentTime = System.currentTimeMillis();
                            if ((currentTime - lastInvokeTime > 5000) || !fileContent.equalsIgnoreCase(lastFileContent)) {
                                lastInvokeTime = currentTime;
                                lastFileContent = fileContent;
                                LOG.info("Processing " + kind.hashCode() + "---" + event + "---" + fileContent + "--" + lastFileContent);
                                if (fileContent.startsWith("UPGRADE")) {
                                    String filePath = fileContent.substring("UPGRADE:".length());
                                    LOG.info(String.format("Upgrade MyCollab with file %s", filePath));
                                    File upgradeFile = new File(filePath);
                                    if (upgradeFile.exists()) {
                                        process.stop();
                                        unpackFile(upgradeFile);
                                        process.start();
                                    } else {
                                        LOG.error("Can not upgrade MyCollab because the upgrade file is not existed " +
                                                upgradeFile.getAbsolutePath());
                                    }
                                } else if (fileContent.startsWith("STOP")) {
                                    process.stop();
                                    LOG.info("Stop wrapper process");
                                    System.exit(-1);
                                } else if (fileContent.equals("RESTART")) {
                                    LOG.info("Restart service ...");
                                    process.stop();
                                    process.start();
                                }
                                LOG.info("Stop processing");
                                break;
                            }
                        }
                    }
                    boolean isValid = key.reset();
                    if (!isValid) {
                        return;
                    }
                }
            } catch (Exception e) {
                LOG.error("Error", e);
            }
        }).start();
        process.start();
    }

    private void stopServer() {
        try (FileWriter writer = new FileWriter(new File(getUserDir(), PID_FILE), false)) {
            writer.write("STOP");
        } catch (Exception e) {
            LOG.error("Error while stopping server", e);
        }
    }

    private static File getUserDir() {
        try {
            CodeSource codeSource = Executor.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            return new File(jarDir);
        } catch (Exception e) {
            return new File(System.getProperty("user.dir"));
        }
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--stop")) {
            new Executor().stopServer();
        } else {
            System.out.println("Length: " + args.length);
            new Executor().runServer(args);
        }
    }
}