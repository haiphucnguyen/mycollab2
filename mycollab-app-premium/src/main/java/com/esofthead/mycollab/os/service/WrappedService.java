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
		
		// System.setProperty("wrapper.config", "wrapper.conf");

		Map<String, String> configuration = new HashMap<>();

		// Temporary folder
		configuration.put("wrapper.tmp.path", "${jna_tmpdir}");

		// Wrapper Logging Properties
		configuration.put("wrapper.console.loglevel", "INFO");
		configuration.put("wrapper.logfile",
				"${wrapper_home}\\/log\\/wrapper.log");
		configuration.put("wrapper.logfile.maxsize", "10m");
		configuration.put("wrapper.logfile.maxfiles", "10");

		configuration.put("wrapper.console.title", APP_NAME);

		// Wrapper Windows Service and Posix Daemon Properties
		configuration.put("wrapper.ntservice.name", APP_NAME);
		configuration.put("wrapper.ntservice.displayname", APP_NAME);
		configuration.put("wrapper.ntservice.description", APP_NAME);

		// Wrapper Posix Daemon Properties
		configuration
				.put("wrapper.daemon.run_level_dir",
						"${if (new File('\\/etc\\/rc0.d').exists()) return '\\/etc\\/rcX.d' else return '\\/etc\\/init.d\\/rcX.d'}");

		// Wrapper System Tray Properties
		configuration.put("wrapper.tray", "true");
		configuration.put("wrapper.tray.port", "15002");
		// configuration.put("wrapper.tray.icon", "favicon.ico");

		// Exit Code Properties
		configuration.put("wrapper.on_exit.0", "SHUTDOWN");
		configuration.put("wrapper.on_exit.default", "RESTART");

		// Trigger actions on console output
		configuration.put("wrapper.filter.trigger.0", "Exception");
		configuration
				.put("wrapper.filter.script.0", "scripts\\/trayMessage.gv");
		configuration.put("wrapper.filter.script.0.args", "Exception");

		// Application settings
		configuration.put("placeHolderSoGenPropsComeHere", "");
		configuration.put("wrapper.java.command", "${JAVA_HOME}/bin/javaw.exe");
		configuration.put("wrapper.app.parameter.1", "--port");
		configuration.put("wrapper.app.parameter.2", "8080");
		configuration.put("wrapper.java.additional.1", "-Xmx1024m");
		configuration.put("wrapper.java.additional.2", "Xms512m");
		configuration.put("wrapper.java.additional.3", "-XX:MaxPermSize=256m");
		configuration
				.put("wrapper.java.additional.4", "-Dfile.encoding=Cp1258");

		// Application main class
		configuration.put("wrapper.java.app.mainclass",
				"com.esofthead.mycollab.jetty.PremiumServerRunner");

		configuration.put("wrapper.app.parameter.1", APP_NAME);

		// Application classpath
		configuration.put(CLASSPATH_KEY + "1", PATH + "runner.jar");
		configuration.put(CLASSPATH_KEY + "lib", LIB_PATH + "*.jar");

		WrappedProcess wrappedProcess = (WrappedProcess) WrappedProcessFactory
				.createProcess(configuration, true);

		wrappedProcess.start();

	}
}
