package com.esofthead.mycollab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.StartedProcess;
import org.zeroturnaround.process.JavaProcess;
import org.zeroturnaround.process.ProcessUtil;
import org.zeroturnaround.process.Processes;

import java.io.File;
import java.io.FileFilter;
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
class MyCollabProcessRunner {
    private static Logger LOG = LoggerFactory.getLogger(MyCollabProcessRunner.class);

    private int processRunningPort;
    private int clientListenPort;
    private JavaProcess wrappedJavaProccess;

    MyCollabProcessRunner(int processRunningPort, int clientListenPort, String stopKey) {
        this.processRunningPort = processRunningPort;
        this.clientListenPort = clientListenPort;
    }

    void start() throws IOException, ExecutionException, InterruptedException {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File workingDir = new File(System.getProperty("user.dir"));
                    File iniFile = new File(workingDir, "bin/mycollab.ini");
                    LOG.info("Load config variables at " + iniFile.getAbsolutePath() + "--" + iniFile.exists());
                    String options = "";
                    if (iniFile.exists()) {
                        Properties properties = new Properties();
                        properties.load(new FileInputStream(iniFile));
                        options = properties.getProperty("MYCOLLAB_OPTS", "");
                        LOG.info("Options in config file: " + options);
                    }

                    List<String> javaOptions = new ArrayList<>();
                    javaOptions.add("java");
                    if (!"".equals(options)) {
                        String[] optArr = options.split(" ");
                        javaOptions.addAll(Arrays.asList(optArr));
                    }

                    File libDir = new File(System.getProperty("user.dir"), "lib");
                    if (!libDir.exists() || libDir.isFile()) {
                        throw new RuntimeException("Can not find the library folder at " + libDir.getAbsolutePath());
                    }
                    StringBuilder classPaths = new StringBuilder();
                    File[] jarFiles = libDir.listFiles(new FileFilter() {
                        @Override
                        public boolean accept(File pathname) {
                            return pathname.isFile() && pathname.getName().endsWith("jar");
                        }
                    });
                    for (int i = 0; i < jarFiles.length; i++) {
                        File subFile = jarFiles[i];
                        classPaths.append(System.getProperty("path.separator"));
                        classPaths.append("./lib/" + subFile.getName());
                    }

                    javaOptions.addAll(Arrays.asList("-cp", classPaths.toString(), "com.esofthead.mycollab.server.DefaultServerRunner", "--port",
                            processRunningPort + "", "--cport", clientListenPort + ""));
                    StringBuilder strBuilder = new StringBuilder();
                    for (String option : javaOptions) {
                        strBuilder.append(option).append(" ");
                    }
                    LOG.info("MyCollab options: " + strBuilder.toString());
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
