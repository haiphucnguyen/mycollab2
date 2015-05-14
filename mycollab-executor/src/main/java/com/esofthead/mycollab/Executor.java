package com.esofthead.mycollab;

import org.rzo.yajsw.config.YajswConfiguration;
import org.rzo.yajsw.wrapper.WrappedProcess;
import org.rzo.yajsw.wrapper.WrappedProcessFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.0.7
 */
public class Executor {
    public static void main(String[] args) {
        System.setProperty("wrapper.java.app.mainclass", "com.esofthead.mycollab.jetty.CommunityServerRunner"); // system properties overwrite properties in conf file.
        System.setProperty("wrapper.appPort", "8080");
        System.setProperty("wrapper.debug", "true");
        System.setProperty("wrapper.debug.level", "3");
        System.setProperty("wrapper.java.classpath.1", "D:\\Documents\\mycollab2\\mycollab-app-community\\target\\staging\\runner.jar");
        Map configuration = new HashMap();
        WrappedProcess w = WrappedProcessFactory.createProcess(configuration, true);
        //process specific configuration
        // initialiase the process
        w.init();
        // start the process
        w.start();
        // a drain warps access to the output and error streams of the wrapped process. The process does not hang if the output is not consumed.
        // a drain must be started and stopped
        // the drain maintains a circular buffer
        // if  since last call to readDrainLine() no new output has been generated then "null" is returned
        // if readDrainLine() is not called for a "long time" the buffer may overflow and you may "miss" some of the output
        // the drain first consumes the error stream, if no output is available the output stream is consumed
        w.startDrain();
        System.out.println(w.readDrainLine());
        w.stopDrain();
        w.waitFor();
        // stop the process
        w.stop();
    }
}
