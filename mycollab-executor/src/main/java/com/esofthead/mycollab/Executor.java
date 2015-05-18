/**
 * This file is part of mycollab-executor.
 *
 * mycollab-executor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-executor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-executor.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;
import org.zeroturnaround.exec.StartedProcess;
import org.zeroturnaround.exec.stream.slf4j.Slf4jStream;
import org.zeroturnaround.process.JavaProcess;
import org.zeroturnaround.process.ProcessUtil;
import org.zeroturnaround.process.Processes;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author MyCollab Ltd
 * @since 5.0.7
 */
public class Executor {
    private static Logger LOG = LoggerFactory.getLogger(Executor.class);

    static class MyCollabWrapProcess {
        StartedProcess javaProcess;
        JavaProcess wrappedJavaProccess;
        int cPort;

        MyCollabWrapProcess(int cPort) {
            this.cPort = cPort;
        }

        void start() throws IOException {
            File workingDir = new File(System.getProperty("user.dir"));
            javaProcess = new ProcessExecutor().command("java", "-jar", "runner.jar", "--cport", cPort + "")
                    .directory(workingDir).redirectOutput(System.out).readOutput(true).start();
            wrappedJavaProccess = Processes.newJavaProcess(javaProcess.getProcess());
        }

        Future<ProcessResult> getFuture() {
            return javaProcess.getFuture();
        }
    }

    private static void unpackFile(File upgradeFile) throws IOException {
        File libFolder = new File(System.getProperty("user.dir"), "lib");
        File webappFolder = new File(System.getProperty("user.dir"), "webapp");
        assertFolderWritePermission(libFolder);
        assertFolderWritePermission(webappFolder);

        org.apache.commons.io.FileUtils.deleteDirectory(libFolder);
        org.apache.commons.io.FileUtils.deleteDirectory(webappFolder);

        byte[] buffer = new byte[2048];

        try (ZipInputStream inputStream = new ZipInputStream(new FileInputStream(upgradeFile))) {
            ZipEntry entry;
            while ((entry = inputStream.getNextEntry()) != null) {
                if (!entry.isDirectory() && (entry.getName().startsWith("lib/")
                        || entry.getName().startsWith("webapp"))) {
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
        final MyCollabWrapProcess myCollabWrapProcess = new MyCollabWrapProcess(listenPort);
        myCollabWrapProcess.start();
        final ExecutorService clientProcessingPool = Executors.newSingleThreadExecutor();
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Socket socket = serverSocket.accept();
                        InputStream inputStream = socket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String request = dataInputStream.readUTF();
                        LOG.info("Receive request " + request);
                        if (request.startsWith("RELOAD")) {
                            String filePath = request.substring("RELOAD:".length());
                            LOG.info("Update MyCollab with file " + filePath);
//                            File upgradeFile = new File(filePath);
                            File upgradeFile = new File("D:\\Documents\\mycollab2\\mycollab-app-community\\target\\upgrade.zip");
                            if (upgradeFile.exists()) {
                                ProcessUtil.destroyGracefullyOrForcefullyAndWait(myCollabWrapProcess.wrappedJavaProccess, 6000, TimeUnit.SECONDS, 6000, TimeUnit.SECONDS);
                                unpackFile(upgradeFile);
                                myCollabWrapProcess.start();
                            } else {
                                LOG.error("Can not upgrade MyCollab because the upgrade file is not existed " + upgradeFile.getAbsolutePath());
                            }
                        }
                    }
                } catch (Exception e) {
                    LOG.warn("Accept failed port " + listenPort, e);
                }

            }
        };
        clientProcessingPool.submit(serverTask);

        Future<ProcessResult> future = myCollabWrapProcess.getFuture();
        ProcessResult proResult = future.get();
    }
}
