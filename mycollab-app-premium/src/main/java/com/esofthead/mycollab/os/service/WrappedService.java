package com.esofthead.mycollab.os.service;

import java.util.HashMap;
import java.util.Map;

import org.rzo.yajsw.wrapper.WrappedProcess;
import org.rzo.yajsw.wrapper.WrappedProcessFactory;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class WrappedService {

	private static final String APP_NAME = "MyCollab Premium/";
	private static final String PATH = "target/mycollab-app-premium-4.5.1-mycollab-dist/mycollab-app-premium-4.5.1/";
	private static final String LIB_PATH = PATH + "lib/";
	private static final String CLASSPATH_KEY = "wrapper.java.classpath.";

	public static void main(String... args) {

		Map<String, String> configuration = new HashMap<>();
		configuration.put("wrapper.java.command",
				"C:\\Program Files\\Java\\jdk1.8.0\\bin\\java.exe");
		configuration
				.put("wrapper.working.dir",
						System.getProperty("user.dir"));
		configuration.put("wrapper.java.app.mainclass",
				"com.esofthead.mycollab.jetty.PremiumServerRunner");
		configuration.put("wrapper.app.parameter.1", "MyCollab");
		configuration.put("wrapper.java.classpath.1", "target/mycollab-app-premium-4.5.1-mycollab-dist/mycollab-app-premium-4.5.1/runner.jar");
		configuration.put("wrapper.java.classpath.2",
				"target/mycollab-app-premium-4.5.1-mycollab-dist/mycollab-app-premium-4.5.1/lib/yajsw-wrapper-11.11.jar");
		configuration.put("wrapper.console.loglevel", "DEBUG");
		configuration.put("wrapper.debug", "true");

		WrappedProcess wrappedProcess = (WrappedProcess) WrappedProcessFactory
				.createProcess(configuration, true);

		wrappedProcess.start();

	}
}
