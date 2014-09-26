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

	private static final String APP_NAME = "MyCollab";
	private static final String PATH = "target/mycollab-app-premium-4.5.1-mycollab-dist/mycollab-app-premium-4.5.1/";

	public static void main(String... args) {

		Map<String, String> configuration = new HashMap<String, String>();
		configuration.put("wrapper.java.command", "java");

		configuration.put("wrapper.tmp.path", "log");
		configuration.put("wrapper.console.loglevel", "DEBUG");
		configuration.put("wrapper.debug", "true");
		configuration.put("wrapper.logfile", "/log/wrapper.log");
		configuration.put("wrapper.logfile.maxsize", "10m");
		configuration.put("wrapper.logfile.maxfiles", "10");

		configuration.put("wrapper.app.parameter.1", APP_NAME);
		configuration.put("wrapper.console.title", APP_NAME);
		configuration.put("wrapper.ntservice.name", APP_NAME);
		configuration.put("wrapper.ntservice.displayname", APP_NAME);
		configuration.put("wrapper.ntservice.description", APP_NAME);

		configuration.put("wrapper.filter.trigger.0", "Exception");

		configuration.put("wrapper.tray", "true");
		configuration.put("wrapper.tray.port", "15002");
		configuration.put("wrapper.tray.icon", PATH + "webapp/favicon.ico");
		configuration.put("wrapper.filter.script.0", "scripts/trayMessage.gv");
		configuration.put("wrapper.filter.script.0.args", "Exception");
		configuration.put("wrapper.filter.script.0.timeout", "1200");

		configuration.put("wrapper.on_exit.0", "SHUTDOWN");
		configuration.put("wrapper.on_exit.default", "RESTART");

		configuration
				.put("wrapper.working.dir", System.getProperty("user.dir"));
		configuration.put("wrapper.java.app.mainclass",
				"com.esofthead.mycollab.jetty.PremiumServerRunner");

		configuration.put("wrapper.java.classpath.1", PATH + "runner.jar");
		configuration.put("wrapper.java.classpath.2", PATH
				+ "lib/yajsw-wrapper-11.11.jar");

		configuration.put("placeHolderSoGenPropsComeHere", "");
		configuration.put("wrapper.app.parameter.1", "--port");
		configuration.put("wrapper.app.parameter.2", "8080");
		configuration.put("wrapper.java.additional.1", "-Xmx1024m");
		configuration.put("wrapper.java.additional.2", "-Xms512m");
		configuration.put("wrapper.java.additional.3", "-XX:MaxPermSize=256m");
		configuration
				.put("wrapper.java.additional.4", "-Dfile.encoding=Cp1258");
		configuration.put("wrapper.java.additional.5",
				"-Djava.net.preferIPv4Stack=true");

		WrappedProcess wrappedProcess = (WrappedProcess) WrappedProcessFactory
				.createProcess(configuration, true);
		wrappedProcess.start();
	}
}
