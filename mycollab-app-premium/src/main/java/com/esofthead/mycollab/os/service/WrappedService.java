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

	public static void main(String... args) {

		// System.setProperty("wrapper.config", "wrapper.conf");

		Map<String, String> configuration = new HashMap<>();
		configuration
				.put("wrapper.working.dir", System.getProperty("user.dir"));
		configuration.put("wrapper.java.app.mainclass",
				"com.esofthead.mycollab.jetty.PremiumServerRunner");
		configuration.put("wrapper.app.parameter.1", "MyCollab");
		configuration
				.put("wrapper.java.classpath.1",
						"target/mycollab-app-premium-4.5.1-mycollab-dist/mycollab-app-premium-4.5.1/runner.jar");
		configuration
				.put("wrapper.java.classpath.2",
						"target/mycollab-app-premium-4.5.1-mycollab-dist/mycollab-app-premium-4.5.1/lib/yajsw-wrapper-11.11.jar");
		configuration
				.put("wrapper.java.classpath.lib",
						"target/mycollab-app-premium-4.5.1-mycollab-dist/mycollab-app-premium-4.5.1/lib/*");

		WrappedProcess wrappedProcess = (WrappedProcess) WrappedProcessFactory
				.createProcess(configuration, true);

		wrappedProcess.start();

	}
}
