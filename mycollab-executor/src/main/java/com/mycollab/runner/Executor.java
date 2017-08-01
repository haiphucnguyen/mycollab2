package com.mycollab.runner;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.CodeSource;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author MyCollab Ltd
 * @since 5.0.7
 */
public class Executor {
    private static Logger LOG = LoggerFactory.getLogger(Executor.class);

    private static void unpackFile(File upgradeFile) throws IOException {
        if (isValidZipFile(upgradeFile)) {
            File libFolder = new File(getUserDir(), "lib");
            File webappFolder = new File(getUserDir(), "webapp");
            File i18nFolder = new File(getUserDir(), "i18n");
            assertFolderWritePermission(libFolder);
            assertFolderWritePermission(webappFolder);
            assertFolderWritePermission(i18nFolder);

            //Hack for windows since the jar files still be keep by process, we will wait until
            // the process is ended actually
            int tryTimes = 0;
            while (tryTimes < 10) {
                try {
                    FileUtils.deleteDirectory(libFolder);
                    FileUtils.deleteDirectory(webappFolder);
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

    private Integer processRunningPort, processPort;
    private String stopKey;
    private String initialOptions;

    private Executor() {
        try {
            File jarFile = getUserDir();
            System.setProperty("MYCOLLAB_APP_HOME", jarFile.getAbsolutePath());
            File iniFile = new File(jarFile, "bin/mycollab.ini");
            LOG.info("Load config variables at " + iniFile.getAbsolutePath() + "--" + iniFile.exists());
            if (iniFile.exists()) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(iniFile));
                initialOptions = properties.getProperty("MYCOLLAB_OPTS", "");
                processRunningPort = Integer.parseInt(properties.getProperty("port", "8080"));
                processPort = Integer.parseInt(properties.getProperty("process_port", "12345"));
                stopKey = properties.getProperty("stop_key", "mycollab");
                LOG.info("Options in config file: " + initialOptions);
            } else {
                LOG.error("Can not find mycollab.ini in path " + iniFile.getAbsolutePath());
                System.exit(-1);
            }
        } catch (Exception e) {
            LOG.error("Error in parsing arguments", e);
            System.exit(-1);
        }
    }

    private void runServer() throws Exception {
        LOG.info("Start MyCollab server process");
        final ServerSocket serverSocket = new ServerSocket(processPort);
        final AppProcess process = new AppProcess(processRunningPort, processPort, initialOptions);
        final ExecutorService clientProcessingPool = Executors.newSingleThreadExecutor();
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        try (Socket socket = serverSocket.accept();
                             InputStream inputStream = socket.getInputStream();
                             DataInputStream dataInputStream = new DataInputStream(inputStream);
                             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                            String request = dataInputStream.readUTF();
                            if (request.startsWith("RELOAD")) {
                                String filePath = request.substring("RELOAD:".length());
                                LOG.info(String.format("Update MyCollab with file %s", filePath));
                                File upgradeFile = new File(filePath);
                                if (upgradeFile.exists()) {
                                    process.stop();
                                    unpackFile(upgradeFile);
                                    process.start();
                                } else {
                                    LOG.error("Can not upgrade MyCollab because the upgrade file is not existed " +
                                            upgradeFile.getAbsolutePath());
                                }
                            } else if (request.startsWith("STOP")) {
                                String key = request.substring("STOP:".length());
                                LOG.info(String.format("Request to terminate MyCollab server with key %s", key));
                                if (stopKey.equals(key)) {
                                    try {
                                        process.stop();
                                        dataOutputStream.writeUTF("Stop success");
                                    } finally {
                                        LOG.info("Stop wrapper process");
                                        System.exit(-1);
                                    }
                                } else {
                                    LOG.info("Invalid stop key " + key + ". It should be " + stopKey);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        clientProcessingPool.submit(serverTask);
        process.start();

    }

    private void stopServer() {
        LOG.info("Kill MyCollab server process");
        try (Socket socket = new Socket("localhost", processPort);
             OutputStream outputStream = socket.getOutputStream();
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            dataOutputStream.writeUTF("STOP:" + stopKey);
            LOG.info("Result: " + dataInputStream.readUTF());
        } catch (Exception e) {
            LOG.error("Error while send RELOAD request to the host process", e);
        } finally {
            System.exit(-1);
        }
    }

    public static void start(String[] args) throws Exception {
        new Executor().runServer();
    }

    public static void stop(String[] args) throws Exception {
        new Executor().stopServer();
    }

    private static File getUserDir(){
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