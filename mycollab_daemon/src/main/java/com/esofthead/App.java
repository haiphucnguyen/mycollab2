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
	private static final String FILE_SEPERATOR;
	
	private static final int TRY_TIMES = 10;

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

		FILE_SEPERATOR = File.separator;
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

		final String yajswPattern = prop.getProperty(CONFIG_KEYS.YAJSW_HOME);
		final String yajswHome = getYajswHome(yajswPattern);

		if (action.equals("install")) {
			prepareConfiguration();

			if (!installService(yajswHome)) {
				System.out
						.println("Install service faile. Please review your configuration and try again...");
				return;
			}

			if (!startService(yajswHome)) {
				System.out
						.println("Start service faile. Please review your configuration and try again...");
			}
		} else if (action.equals("start")) {
			if (!startService(yajswHome)) {
				System.out
						.println("Start service faile. Please review your configuration and try again...");
			}
		} else if (action.equals("stop")) {
			if (!stopService(yajswHome)) {
				System.out
						.println("Stop service faile. Please review your configuration and try again...");
			}
		} else if (action.equals("uninstall")) {
			/* first stop service */
			if (!stopService(yajswHome)) {
				System.out
						.println("Stop service faile. Please review your configuration and try again...");

				return;
			}

			if (!uninstallService(yajswHome)) {
				System.out
						.println("Uninstall service faile. Please review your configuration and try again...");
				return;
			}
		} else {
			System.err
					.println("usage: java -jar [Jar Package] <[start]|[stop]|[install]|[uninstall]>");
		}
	}

	private static final void prepareConfiguration() throws Exception {
		final String yajswHome = getYajswHome(prop
				.getProperty(CONFIG_KEYS.YAJSW_HOME));
		final String yajswConfig = getYajswConf(yajswHome);

		Properties yajswProp = new Properties();
		yajswProp.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(YAJSW_CONFIG_FILE));

		yajswProp.setProperty(CONFIG_KEYS.WORKING_DIR, getWorkingDir());

		yajswProp.setProperty(CONFIG_KEYS.CONSOLE_TITLE,
				prop.getProperty(CONFIG_KEYS.CONSOLE_TITLE));
		yajswProp.setProperty(CONFIG_KEYS.NT_SERVICE_NAME,
				prop.getProperty(CONFIG_KEYS.NT_SERVICE_NAME));
		yajswProp.setProperty(CONFIG_KEYS.NT_SERVICE_DISPLAYNAME,
				prop.getProperty(CONFIG_KEYS.NT_SERVICE_DISPLAYNAME));
		yajswProp.setProperty(CONFIG_KEYS.NT_SERVICE_DESCRIPTION,
				prop.getProperty(CONFIG_KEYS.NT_SERVICE_DESCRIPTION));

		yajswProp.setProperty(CONFIG_KEYS.JAVA_CMD, getJavaCmd());
		yajswProp
				.setProperty(
						CONFIG_KEYS.APP_JAR,
						prop.getProperty(CONFIG_KEYS.APP_JAR)
								.replace(ENVIROMENT_VARS.WORKING_FOLDER,
										getWorkingDir())
								.replace(ENVIROMENT_VARS.TEMP_FOLDER,
										getTmpFolder())
								.replace(ENVIROMENT_VARS.FILE_SEPERATOR,
										FILE_SEPERATOR));

		for (int i = 1; i <= 50; i++) {
			final String key = String.format("%s.%d", CONFIG_KEYS.ARG, i);
			String arg = prop.getProperty(key);

			if (null != arg && arg.trim().length() > 0) {
				yajswProp.remove(key);
				yajswProp.put(
						key,
						arg.replace(ENVIROMENT_VARS.WORKING_FOLDER,
								getWorkingDir())
								.replace(ENVIROMENT_VARS.TEMP_FOLDER,
										getTmpFolder())
								.replace(ENVIROMENT_VARS.FILE_SEPERATOR,
										FILE_SEPERATOR));
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
								getWorkingDir())
								.replace(ENVIROMENT_VARS.TEMP_FOLDER,
										getTmpFolder())
								.replace(ENVIROMENT_VARS.FILE_SEPERATOR,
										FILE_SEPERATOR));
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

	private static final String getTmpFolder() {
		return System.getProperty("java.io.tmpdir");
	}

	private static final String getWorkingDir() {
		final String currentDir = getUserDir();

		File file = new File(currentDir);
		return file.getParent();
	}

	private static final String getYajswHome(String yajswPattern) {
		final String workingDir = getWorkingDir();
		if (workingDir.endsWith(FILE_SEPERATOR))
			return String.format("%s%s", workingDir, yajswPattern);
		return String
				.format("%s%s%s", workingDir, FILE_SEPERATOR, yajswPattern);
	}

	private static final String getJavaCmd() {
		String javaHome = System.getProperty("java.home");

		if (javaHome.endsWith(FILE_SEPERATOR))
			return String.format("%sbin%sjava", javaHome, FILE_SEPERATOR);
		return String.format("%s%sbin%sjava", javaHome, FILE_SEPERATOR,
				FILE_SEPERATOR);
	}

	private static final String getYajswConf(String yajswHome) {
		File file = new File(yajswHome);
		if (file.exists()) {
			if (yajswHome.endsWith(FILE_SEPERATOR))
				return String.format("%sconf%swrapper.conf", yajswHome,
						FILE_SEPERATOR);
			return String.format("%s%sconf%swrapper.conf", yajswHome,
					FILE_SEPERATOR, FILE_SEPERATOR);
		}

		final String yajswPattern = getYajswHome(yajswHome);
		file = new File(yajswPattern);
		if (file.exists())
			return getYajswConf(yajswPattern);

		return "";
	}

	private static final String getInstallCmd(String yajswHome) {
		if (__OsType == OsType.WINDOWS) {
			if (yajswHome.endsWith(FILE_SEPERATOR)) {
				return String.format("%sbat%sinstallService.bat", yajswHome,
						FILE_SEPERATOR);
			}

			return String.format("%s%sbat%sinstallService.bat", yajswHome,
					FILE_SEPERATOR, FILE_SEPERATOR);
		} else {
			if (yajswHome.endsWith(FILE_SEPERATOR)) {
				return String.format("%sbin%sinstallDaemon.sh", yajswHome,
						FILE_SEPERATOR);
			}

			return String.format("%s%sbin%sinstallDaemon.sh", yajswHome,
					FILE_SEPERATOR, FILE_SEPERATOR);
		}
	}

	private static final String getStartCmd(String yajswHome) {
		if (__OsType == OsType.WINDOWS) {
			if (yajswHome.endsWith(FILE_SEPERATOR)) {
				return String.format("%sbat%sstartService.bat", yajswHome,
						FILE_SEPERATOR);
			}

			return String.format("%s%sbat%sstartService.bat", yajswHome,
					FILE_SEPERATOR, FILE_SEPERATOR);
		} else {
			if (yajswHome.endsWith(FILE_SEPERATOR)) {
				return String.format("%sbin%sstartDaemon.sh", yajswHome,
						FILE_SEPERATOR);
			}

			return String.format("%s%sbin%sstartDaemon.sh", yajswHome,
					FILE_SEPERATOR, FILE_SEPERATOR);
		}
	}

	private static final String getStopCmd(String yajswHome) {
		if (__OsType == OsType.WINDOWS) {
			if (yajswHome.endsWith(FILE_SEPERATOR)) {
				return String.format("%sbat%sstopService.bat", yajswHome,
						FILE_SEPERATOR);
			}

			return String.format("%s%sbat%sstopService.bat", yajswHome,
					FILE_SEPERATOR, FILE_SEPERATOR);
		} else {
			if (yajswHome.endsWith(FILE_SEPERATOR)) {
				return String.format("%sbin%sstopDaemon.sh", yajswHome,
						FILE_SEPERATOR);
			}

			return String.format("%s%sbin%sstopDaemon.sh", yajswHome,
					FILE_SEPERATOR, FILE_SEPERATOR);
		}
	}

	private static final String getUninstallCmd(String yajswHome) {
		if (__OsType == OsType.WINDOWS) {
			if (yajswHome.endsWith(FILE_SEPERATOR)) {
				return String.format("%sbat%suninstallService.bat", yajswHome,
						FILE_SEPERATOR);
			}

			return String.format("%s%sbat%suninstallService.bat", yajswHome,
					FILE_SEPERATOR, FILE_SEPERATOR);
		} else {
			if (yajswHome.endsWith(FILE_SEPERATOR)) {
				return String.format("%sbin%suninstallDaemon.sh", yajswHome,
						FILE_SEPERATOR);
			}

			return String.format("%s%sbin%suninstallDaemon.sh", yajswHome,
					FILE_SEPERATOR, FILE_SEPERATOR);
		}
	}

	private static final String getOutPut(String scriptHome, String command)
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

	private static final boolean startService(String yajswHome)
			throws Exception {
		int __try = 0;
		String errorCode = "";
		while (__try < TRY_TIMES) {
			System.out.println("\r\nTry times: " + (__try + 1));
			errorCode = getOutPut(yajswHome, getStartCmd(yajswHome));
			if (errorCode.toLowerCase().indexOf("not start") != -1) {
				__try++;
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
					/*
					 * Let this error pass
					 */
				}
				continue;
			}
			break;
		}
		if (errorCode.toLowerCase().indexOf("not start") != -1) {
			return false;
		}

		return true;
	}

	private static final boolean stopService(String yajswHome) throws Exception {
		int __try = 0;
		String errorCode = "";
		while (__try < TRY_TIMES) {
			System.out.println("\r\nTry times: " + (__try + 1));
			errorCode = getOutPut(yajswHome, getStopCmd(yajswHome));
			if (errorCode.toLowerCase().indexOf("not stop") != -1) {
				__try++;
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
					/*
					 * Let this error pass
					 */
				}				
				continue;
			}
			break;
		}
		if (errorCode.toLowerCase().indexOf("not stop") != -1) {
			return false;
		}
		return true;
	}

	private static final boolean uninstallService(String yajswHome)
			throws Exception {
		int __try = 0;
		String errorCode = "";
		while (__try < TRY_TIMES) {
			System.out.println("\r\nTry times: " + (__try + 1));
			errorCode = getOutPut(yajswHome, getUninstallCmd(yajswHome));
			if (errorCode.toLowerCase().indexOf("not removed") != -1) {
				__try++;
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
					/*
					 * Let this error pass
					 */
				}
				continue;
			}
			break;
		}
		if (errorCode.toLowerCase().indexOf("not removed") != -1) {
			return false;
		}
		return true;
	}

	private static final boolean installService(String yajswHome)
			throws Exception {
		int __try = 0;
		String errorCode = "";
		while (__try < TRY_TIMES) {
			System.out.println("\r\nTry times: " + (__try + 1));
			errorCode = getOutPut(yajswHome, getInstallCmd(yajswHome));
			if (errorCode.toLowerCase().indexOf("not install") != -1) {
				__try++;
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
					/*
					 * Let this error pass
					 */
				}
				continue;
			}
			break;
		}
		if (errorCode.toLowerCase().indexOf("not install") != -1) {
			return false;
		}
		return true;
	}

}
