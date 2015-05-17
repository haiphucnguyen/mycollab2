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

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

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
            javaProcess = new ProcessExecutor().command("java", "-jar", "D:\\Documents\\mycollab2\\mycollab-app-community\\target\\staging\\runner.jar", "--cport", cPort + "")
                    .directory(new File("D:\\Documents\\mycollab2\\mycollab-app-community\\target\\staging"))
                    .redirectOutput(Slf4jStream.of(Executor.class).asDebug()).readOutput(true).start();
            wrappedJavaProccess = Processes.newJavaProcess(javaProcess.getProcess());
        }

        void restart() throws InterruptedException, TimeoutException, IOException {
            if (wrappedJavaProccess != null) {
                ProcessUtil.destroyGracefullyOrForcefullyAndWait(wrappedJavaProccess, 30, TimeUnit.SECONDS, 10, TimeUnit.SECONDS);
                start();
            }
        }

        Future<ProcessResult> getFuture() {
            return javaProcess.getFuture();
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
                        if ("RELOAD".equals(request)) {
                            myCollabWrapProcess.restart();
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
