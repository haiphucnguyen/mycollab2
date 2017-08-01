package com.mycollab.runner;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.CodeSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author MyCollab Ltd
 * @since 5.0.7
 */
public class Executor {
    private static Logger LOG = LoggerFactory.getLogger(Executor.class);

    private MyCollabProcess process;

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
                    if (!entry.isDirectory() && (entry.getName().startsWith("lib/") || entry.getName().startsWith
                            ("webapp") || entry.getName().startsWith("i18n"))) {
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


    Executor() {
        try {
            File jarFile = getUserDir();
            System.setProperty("MYCOLLAB_APP_HOME", jarFile.getAbsolutePath());
        } catch (Exception e) {
            LOG.error("Error in parsing arguments", e);
            System.exit(-1);
        }
    }

    private void runServer() throws Exception {
        LOG.info("Start MyCollab server process");
        process = new MyCollabProcess("");
        process.start();
    }

    private void stopServer() {
        try {
            process.stop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(-1);
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

    public static void main(String[] args) throws Exception {
        if (args.length > 0 && (args[0].equals("stop") || args[0].equals("--stop"))) {
            new Executor().stopServer();
        } else {
            new Executor().runServer();
        }
    }
}
