package com.esofthead.mycollab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.StartedProcess;
import org.zeroturnaround.process.JavaProcess;
import org.zeroturnaround.process.ProcessUtil;
import org.zeroturnaround.process.Processes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author MyCollab Ltd.
 * @since 5.0.7
 */
class CoreProcess {
    private static Logger LOG = LoggerFactory.getLogger(CoreProcess.class);

    private int processRunningPort;
    private int clientListenPort;
    private JavaProcess wrappedJavaProccess;
    private Thread mainThread;

    CoreProcess(int processRunningPort, int clientListenPort) {
        this.processRunningPort = processRunningPort;
        this.clientListenPort = clientListenPort;
    }

    void start() throws IOException, ExecutionException, InterruptedException {
        mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File workingDir = new File(System.getProperty("user.dir"));
                    File iniFile = new File(workingDir, "bin/mycollab.ini");
                    String options = "";
                    if (iniFile.exists()) {
                        Properties properties = new Properties();
                        properties.load(new FileInputStream(iniFile));
                        options = properties.getProperty("MYCOLLAB_OPTS", "");
                    }

                    List<String> javaOptions = new ArrayList<>();
                    javaOptions.add("java");
                    if (!"".equals(options)) {
                        String[] optArr = options.split(" ");
                        javaOptions.addAll(Arrays.asList(optArr));
                    }
                    javaOptions.addAll(Arrays.asList("-jar", "runner.jar", "--port", processRunningPort + "", "--cport", clientListenPort + ""));

                    StartedProcess javaProcess = new ProcessExecutor().command(javaOptions.toArray(new String[javaOptions.size()]))
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
        LOG.info("Stopping MyCollab process");
        ProcessUtil.destroyGracefullyOrForcefullyAndWait(wrappedJavaProccess, 6000, TimeUnit.SECONDS, 6000, TimeUnit.SECONDS);
        LOG.info("Stopped MyCollab process successfully");
    }
}
