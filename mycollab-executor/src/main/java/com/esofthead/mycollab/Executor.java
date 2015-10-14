package com.esofthead.mycollab;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(file);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (zipfile != null) {
                    zipfile.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private static void assertFolderWritePermission(File folder) throws IOException {
        if (!folder.canWrite()) {
            throw new IOException(System.getProperty("user.name") + " does not have write permission on folder " + folder.getAbsolutePath()
                    + ". The upgrade could not be proceeded. Please correct permission before you upgrade MyCollab again");
        }
    }

    public static void main(String[] args) throws Exception {
        final ServerSocket serverSocket = new ServerSocket(0);
        final int listenPort = serverSocket.getLocalPort();
        LOG.info("Open server port: " + listenPort);

        int processRunningPort = 8080;
        try {
            for (int i = 0; i < args.length; i++) {
                if ("--port".equals(args[i])) {
                    processRunningPort = Integer.parseInt(args[++i]);
                }
            }
        } catch (Exception e) {
            LOG.error("Error in parsing arguments", e);
        }
        final CoreProcess process = new CoreProcess(processRunningPort, listenPort);

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
}
