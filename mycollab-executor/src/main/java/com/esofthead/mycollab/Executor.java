package com.esofthead.mycollab;

import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;
import org.zeroturnaround.exec.StartedProcess;
import org.zeroturnaround.exec.stream.slf4j.Slf4jStream;
import org.zeroturnaround.process.JavaProcess;
import org.zeroturnaround.process.Processes;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author MyCollab Ltd
 * @since 5.0.7
 */
public class Executor {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(0);
        final int listenPort = serverSocket.getLocalPort();

        StartedProcess javaProcess = new ProcessExecutor().command("java", "-jar", "D:\\Documents\\mycollab2\\mycollab-app-community\\target\\staging\\runner.jar", "-cport", listenPort+"")
                .directory(new File("D:\\Documents\\mycollab2\\mycollab-app-community\\target\\staging"))
                .redirectOutput(Slf4jStream.of(Executor.class).asDebug()).readOutput(true).start();

        final JavaProcess wrappedJavaProccess = Processes.newJavaProcess(javaProcess.getProcess());
        final ExecutorService clientProcessingPool = Executors.newSingleThreadExecutor();
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(listenPort);
                    while (true) {
                        Socket socket = serverSocket.accept();

                    }
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                }

            }
        };
        clientProcessingPool.submit(serverTask);

        Future<ProcessResult> future = javaProcess.getFuture();
        ProcessResult proResult = future.get();
    }
}
