package com.esofthead.mycollab;

import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.StartedProcess;
import org.zeroturnaround.process.JavaProcess;
import org.zeroturnaround.process.ProcessUtil;
import org.zeroturnaround.process.Processes;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author MyCollab Ltd.
 * @since 5.0.7
 */
class CoreProcess {
    private int port;
    private JavaProcess wrappedJavaProccess;
    private Thread mainThread;

    CoreProcess(int port) {
        this.port = port;
    }

    void start() throws IOException, ExecutionException, InterruptedException {
        mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File workingDir = new File(System.getProperty("user.dir"));
                    StartedProcess javaProcess = new ProcessExecutor().command("java", "-jar", "runner.jar", "--cport", port + "")
                            .directory(workingDir).redirectOutput(System.out).readOutput(true).start();
                    wrappedJavaProccess = Processes.newJavaProcess(javaProcess.getProcess());
                    javaProcess.getFuture().get();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        });
        mainThread.start();
    }

    void stop() throws InterruptedException, TimeoutException, IOException {
        System.out.println("Stopping MyCollab process");
        ProcessUtil.destroyGracefullyOrForcefullyAndWait(wrappedJavaProccess, 6000, TimeUnit.SECONDS, 6000, TimeUnit.SECONDS);
        System.out.println("Stopped MyCollab process successfully");
    }
}
