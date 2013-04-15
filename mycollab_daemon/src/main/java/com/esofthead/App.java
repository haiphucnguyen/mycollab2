package com.esofthead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class App {

	private static final String CONFIG_FILE = "config.properties";
	private static final String YAJSW_CONFIG_FILE = "yajsw.properties";
	private static Properties prop;

	static class ENVIROMENT_VARS {
		public static final String WORKING_FOLDER = "${WORKING_FOLDER}";
		public static final String TEMP_FOLDER = "${TEMP_FOLDER}";
		public static final String FILE_SEPERATOR = "${SEPERATOR}";
	}

	static class CONFIG_KEYS {
		public static final String WORKING_DIR = "wrapper.working.dir";
		public static final String CONSOLE_TITLE = "wrapper.console.title";
		public static final String NT_SERVICE_NAME = "wrapper.ntservice.name";
		public static final String NT_SERVICE_DISPLAYNAME = "wrapper.ntservice.displayname";
		public static final String NT_SERVICE_DESCRIPTION = "wrapper.ntservice.description";
		public static final String APP_JAR = "wrapper.java.app.jar";
		public static final String JAVA_CMD = "wrapper.java.command";

		public static final String ARG = "wrapper.app.parameter";
		public static final String JAVA_ADDITIONAL = "wrapper.java.additional";

		public static final String YAJSW_HOME = "yajsw.home";
		public static final String APP_HOME = "app.home";
	}

	enum OsType {
		UNIX, WINDOWS
	}

	private static OsType __OsType;
	static {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.indexOf("win") != -1)
			__OsType = OsType.WINDOWS;
		else
			__OsType = OsType.UNIX;
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err
					.println("usage: java -jar [Jar Package] <[start]|[stop]|[install]|[uninstall]>");
			return;
		}

		String action = args[0];
		prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(CONFIG_FILE));

		final String yajswHome = prop.getProperty(CONFIG_KEYS.YAJSW_HOME);

		String errorCode = null;

		if (action.equals("install")) {
			prepareConfiguration();

			errorCode = getOutPut(getScriptHome(yajswHome),
					getInstallCmd(yajswHome));
			if (errorCode.toLowerCase().indexOf("not install") != -1) {
				/* there have error occur */
				System.out
						.println("Install service faile. Please review your configuration and try again...");
				return;
			}

			errorCode = getOutPut(getScriptHome(yajswHome),
					getStartCmd(yajswHome));
			if (errorCode.toLowerCase().indexOf("not start") != -1) {
				System.out
						.println("Start service faile. Please review your configuration and try again...");
				return;
			}
		} else if (action.equals("start")) {
			errorCode = getOutPut(getScriptHome(yajswHome),
					getStartCmd(yajswHome));
			if (errorCode.toLowerCase().indexOf("not start") != -1) {
				System.out
						.println("Start service faile. Please review your configuration and try again...");
				return;
			}
		} else if (action.equals("stop")) {
			errorCode = getOutPut(getScriptHome(yajswHome),
					getStopCmd(yajswHome));
			if (errorCode.toLowerCase().indexOf("not stop") != -1) {
				System.out
						.println("Stop service faile. Please review your configuration and try again...");
				return;
			}
		} else if (action.equals("uninstall")) {
			/* first stop service */
			getOutPut(getScriptHome(yajswHome), getStopCmd(yajswHome));

			errorCode = getOutPut(getScriptHome(yajswHome),
					getUninstallCmd(yajswHome));
			if (errorCode.toLowerCase().indexOf("not removed") != -1) {
				System.out
						.println("Stop service faile. Please review your configuration and try again...");
				return;
			}
		} else {
			System.err
					.println("usage: java -jar [Jar Package] <[start]|[stop]|[install]|[uninstall]>");
		}
	}

	private static final void prepareConfiguration() throws Exception {
		final String yajswHome = prop.getProperty(CONFIG_KEYS.YAJSW_HOME);
		final String yajswConfig = getYajswConf(yajswHome);

		Properties yajswProp = new Properties();
		yajswProp.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(YAJSW_CONFIG_FILE));

		yajswProp.setProperty(CONFIG_KEYS.WORKING_DIR,
				getWorkingDir(prop.getProperty(CONFIG_KEYS.WORKING_DIR)));

		yajswProp.setProperty(CONFIG_KEYS.CONSOLE_TITLE,
				prop.getProperty(CONFIG_KEYS.CONSOLE_TITLE));
		yajswProp.setProperty(CONFIG_KEYS.NT_SERVICE_NAME,
				prop.getProperty(CONFIG_KEYS.NT_SERVICE_NAME));
		yajswProp.setProperty(CONFIG_KEYS.NT_SERVICE_DISPLAYNAME,
				prop.getProperty(CONFIG_KEYS.NT_SERVICE_DISPLAYNAME));
		yajswProp.setProperty(CONFIG_KEYS.NT_SERVICE_DESCRIPTION,
				prop.getProperty(CONFIG_KEYS.NT_SERVICE_DESCRIPTION));

		yajswProp.setProperty(CONFIG_KEYS.JAVA_CMD, getJavaCmd());
		yajswProp.setProperty(
				CONFIG_KEYS.APP_JAR,
				prop.getProperty(CONFIG_KEYS.APP_JAR)
						.replace(ENVIROMENT_VARS.WORKING_FOLDER, getUserDir())
						.replace(ENVIROMENT_VARS.TEMP_FOLDER, getTmpFolder()));

		for (int i = 1; i <= 50; i++) {
			final String key = String.format("%s.%d", CONFIG_KEYS.ARG, i);
			String arg = prop.getProperty(key);

			if (null != arg && arg.trim().length() > 0) {
				yajswProp.remove(key);
				yajswProp.put(
						key,
						arg.replace(ENVIROMENT_VARS.WORKING_FOLDER,
								getUserDir())
								.replace(ENVIROMENT_VARS.TEMP_FOLDER,
										getTmpFolder())
								.replace(ENVIROMENT_VARS.FILE_SEPERATOR,
										File.separator));
			} else {
				break;
			}
		}

		for (int i = 1; i <= 50; i++) {
			final String key = String.format("%s.%d",
					CONFIG_KEYS.JAVA_ADDITIONAL, i);
			String arg = prop.getProperty(key);
			if (null != arg && arg.trim().length() > 0) {
				yajswProp.remove(key);
				yajswProp.put(
						key,
						arg.replace(ENVIROMENT_VARS.WORKING_FOLDER,
								getUserDir())
								.replace(ENVIROMENT_VARS.TEMP_FOLDER,
										getTmpFolder())
								.replace(ENVIROMENT_VARS.FILE_SEPERATOR,
										File.separator));
			} else {
				break;
			}
		}

		yajswProp.store(new FileOutputStream(new File(yajswConfig)),
				"Build configuration for service");
	}

	private static final String getUserDir() {
		return System.getProperty("user.dir");
	}

	private static final String getWorkingDir(String folder) {
		final String currentDir = getUserDir();
		if (null == folder || folder.trim().length() == 0) {
			return currentDir;
		}
		if (__OsType == OsType.WINDOWS) {
			if (currentDir.endsWith("\\")) {
				return currentDir + folder;
			}

			return String.format("%s\\%s", currentDir, folder);
		} else {
			if (currentDir.endsWith("/")) {
				return currentDir + folder;
			}

			return String.format("%s/%s", currentDir, folder);
		}
	}

	private static final String getJavaCmd() {
		String javaHome = System.getProperty("java.home");
		if (__OsType == OsType.WINDOWS) {
			if (javaHome.endsWith("\\")) {
				return javaHome + "bin\\java";
			}

			return String.format("%s\\bin\\java", javaHome);
		} else {
			if (javaHome.endsWith("/")) {
				return javaHome + "bin/java";
			}

			return String.format("%s/bin/java", javaHome);
		}
	}

	private static final String getYajswConf(String yajswHome) {
		final String currentDir = getUserDir();
		if (__OsType == OsType.WINDOWS) {
			if (currentDir.endsWith("\\")) {
				return String.format("%s%s\\conf\\wrapper.conf", currentDir,
						yajswHome);
			}

			return String.format("%s\\%s\\conf\\wrapper.conf", currentDir,
					yajswHome);
		} else {
			if (currentDir.endsWith("/")) {
				return String.format("%s%s/conf/wrapper.conf", currentDir,
						yajswHome);
			}

			return String.format("%s/%s/conf/wrapper.conf", currentDir,
					yajswHome);
		}
	}

	private static final String getTmpFolder() {
		return System.getProperty("java.io.tmpdir");
	}

	private static final String getInstallCmd(String yajswHome) {
		final String currentDir = getUserDir();
		if (__OsType == OsType.WINDOWS) {
			if (currentDir.endsWith("\\")) {
				return String.format("%s%s\\bat\\installService.bat",
						currentDir, yajswHome);
			}

			return String.format("%s\\%s\\bat\\installService.bat", currentDir,
					yajswHome);
		} else {
			if (currentDir.endsWith("/")) {
				return String.format("%s%s/bin/installDaemon.sh", currentDir,
						yajswHome);
			}

			return String.format("%s/%s/bin/installDaemon.sh", currentDir,
					yajswHome);
		}
	}

	private static final String getStartCmd(String yajswHome) {
		final String currentDir = getUserDir();
		if (__OsType == OsType.WINDOWS) {
			if (currentDir.endsWith("\\")) {
				return String.format("%s%s\\bat\\startService.bat", currentDir,
						yajswHome);
			}

			return String.format("%s\\%s\\bat\\startService.bat", currentDir,
					yajswHome);
		} else {
			if (currentDir.endsWith("/")) {
				return String.format("%s%s/bin/startDaemon.sh", currentDir,
						yajswHome);
			}

			return String.format("%s/%s/bin/startDaemon.sh", currentDir,
					yajswHome);
		}
	}

	private static final String getStopCmd(String yajswHome) {
		final String currentDir = getUserDir();
		if (__OsType == OsType.WINDOWS) {
			if (currentDir.endsWith("\\")) {
				return String.format("%s%s\\bat\\stopService.bat", currentDir,
						yajswHome);
			}

			return String.format("%s\\%s\\bat\\stopService.bat", currentDir,
					yajswHome);
		} else {
			if (currentDir.endsWith("/")) {
				return String.format("%s%s/bin/stopDaemon.sh", currentDir,
						yajswHome);
			}

			return String.format("%s/%s/bin/stopDaemon.sh", currentDir,
					yajswHome);
		}
	}

	private static final String getUninstallCmd(String yajswHome) {
		final String currentDir = getUserDir();
		if (__OsType == OsType.WINDOWS) {
			if (currentDir.endsWith("\\")) {
				return String.format("%s%s\\bat\\uninstallService.bat",
						currentDir, yajswHome);
			}

			return String.format("%s\\%s\\bat\\uninstallService.bat",
					currentDir, yajswHome);
		} else {
			if (currentDir.endsWith("/")) {
				return String.format("%s%s/bin/uninstallDaemon.sh", currentDir,
						yajswHome);
			}

			return String.format("%s/%s/bin/uninstallDaemon.sh", currentDir,
					yajswHome);
		}
	}

	private static final String getScriptHome(String yajswHome) {
		final String currentDir = getUserDir();
		if (__OsType == OsType.WINDOWS) {
			if (currentDir.endsWith("\\")) {
				return String.format("%s%s\\bat", currentDir, yajswHome);
			}

			return String.format("%s\\%s\\bat", currentDir, yajswHome);
		} else {
			if (currentDir.endsWith("/")) {
				return String.format("%s%s/bin", currentDir, yajswHome);
			}

			return String.format("%s/%s/bin", currentDir, yajswHome);
		}
	}

	private static String getOutPut(String scriptHome, String command)
			throws Exception {
		final ProcessBuilder pb; // = new ProcessBuilder("cmd.exe", "/c",
									// command);
		if (__OsType == OsType.WINDOWS) {
			pb = new ProcessBuilder("cmd.exe", "/c", command);
		} else {
			pb = new ProcessBuilder(command);
		}
		pb.directory(new File(scriptHome));
		final Process p = pb.start();

		StringBuilder result = new StringBuilder("");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			result.append(line);
		}
		reader.close();

		if (result.length() == 0) {
			reader = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				result.append(line + "\n");
			}
			reader.close();
		}

		return result.toString();
	}

}
