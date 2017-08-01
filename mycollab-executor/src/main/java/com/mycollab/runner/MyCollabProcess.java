package com.mycollab.runner;

import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.StartedProcess;
import org.zeroturnaround.process.JavaProcess;
import org.zeroturnaround.process.ProcessUtil;
import org.zeroturnaround.process.Processes;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author MyCollab Ltd.
 * @since 5.0.7
 */
class MyCollabProcess {
    private static Logger LOG = LoggerFactory.getLogger(MyCollabProcess.class);
    private String initialOptions;
    private JavaProcess wrappedJavaProcess;

    MyCollabProcess(String initialOptions) {
        this.initialOptions = initialOptions;
    }

    void start() throws IOException, ExecutionException, InterruptedException {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File workingDir = new File(System.getProperty("MYCOLLAB_APP_HOME"));
                    List<String> javaOptions = new ArrayList<>();
                    String javaHomePath = System.getProperty("java.home");
                    String javaPath;
                    if (SystemUtils.IS_OS_WINDOWS) {
                        javaPath = javaHomePath + "/bin/java.exe";
                    } else {
                        javaPath = javaHomePath + "/bin/java";
                    }
                    File javaExecutableFile = new File(javaPath);
                    if (javaExecutableFile.exists()) {
                        javaOptions.add(javaExecutableFile.getAbsolutePath());
                    } else {
                        javaOptions.add("java");
                    }


                    if (!"".equals(initialOptions)) {
                        String[] optArr = initialOptions.split(" ");
                        javaOptions.addAll(Arrays.asList(optArr));
                    }

                    File libDir = new File(workingDir, "lib");
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

                    for (File subFile : jarFiles) {
                        classPaths.append(System.getProperty("path.separator"));
                        classPaths.append("./lib/" + subFile.getName());
                    }

                    javaOptions.addAll(Arrays.asList("-cp", classPaths.toString(), "com.mycollab.server.DefaultServerRunner"));
                    StringBuilder strBuilder = new StringBuilder();
                    for (String option : javaOptions) {
                        strBuilder.append(option).append(" ");
                    }
                    LOG.info(strBuilder.toString());
                    StartedProcess javaProcess = new ProcessExecutor().command(javaOptions.toArray(new String[javaOptions.size()]))
                            .directory(workingDir).redirectOutput(System.out).readOutput(true).start();

                    wrappedJavaProcess = Processes.newJavaProcess(javaProcess.getProcess());
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
        ProcessUtil.destroyGracefullyOrForcefullyAndWait(wrappedJavaProcess, 6000, TimeUnit.SECONDS, 6000, TimeUnit.SECONDS);
        LOG.info("Stopped MyCollab process successfully");
    }
}
