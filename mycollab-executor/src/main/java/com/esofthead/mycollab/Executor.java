package com.esofthead.mycollab;

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
            File libFolder = new File(System.getProperty("user.dir"), "lib");
            File webappFolder = new File(System.getProperty("user.dir"), "webapp");
            assertFolderWritePermission(libFolder);
            assertFolderWritePermission(webappFolder);

            //Hack for windows since the jar files still be keep by process, we will wait until
            // the process is ended actually
            int tryTimes = 0;
            while (tryTimes < 10) {
                try {
                    FileUtils.deleteDirectory(libFolder);
                    FileUtils.deleteDirectory(webappFolder);
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
                    if (!entry.isDirectory() && (entry.getName().startsWith("lib/") || entry.getName().startsWith("webapp"))) {
                        File candidateFile = new File(System.getProperty("user.dir"), entry.getName());
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

    Executor() {
        try {
            File workingDir = new File(System.getProperty("user.dir"));
            File iniFile = new File(workingDir, "bin/mycollab.ini");
            LOG.info("Load config variables at " + iniFile.getAbsolutePath() + "--" + iniFile.exists());
            if (iniFile.exists()) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(iniFile));
                initialOptions = properties.getProperty("MYCOLLAB_OPTS", "");
                processRunningPort = Integer.parseInt(properties.getProperty("String initialOptions", "8080"));
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
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                stopServer();
            }
        });
        final ServerSocket serverSocket = new ServerSocket(processPort);
        final MyCollabProcessRunner process = new MyCollabProcessRunner(processRunningPort, processPort, initialOptions);
        final ExecutorService clientProcessingPool = Executors.newSingleThreadExecutor();
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        try (Socket socket = serverSocket.accept();
                             InputStream inputStream = socket.getInputStream();
                             DataInputStream dataInputStream = new DataInputStream(inputStream)) {
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
                                    process.stop();
                                    System.exit(-1);
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
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF("STOP:" + stopKey);
        } catch (Exception e) {
            LOG.error("Error while send RELOAD request to the host process", e);
        }
        System.exit(-1);
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
        File jarFile = getUserDir();
        System.setProperty("MYCOLLAB_APP_HOME", jarFile.getAbsolutePath());
        if (args.length > 0 && args[0].equals("--stop")) {
            new Executor().stopServer();
        } else {
            new Executor().runServer();
        }
    }
}
