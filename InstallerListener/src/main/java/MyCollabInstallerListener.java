import java.io.File;

import com.izforge.izpack.event.SimpleInstallerListener;
import com.izforge.izpack.installer.AutomatedInstallData;
import com.izforge.izpack.util.AbstractUIProgressHandler;


public class MyCollabInstallerListener extends SimpleInstallerListener {
	static final String DAEMON_JAR = "mycollab-daemon.jar";

	@Override
	public void afterPacks(AutomatedInstallData arg0,
			AbstractUIProgressHandler arg1) throws Exception {
		final String installPath = arg0.getInstallPath();
		final String javaHome = System.getProperty("java.home");
		final String javaCmd = String.format("%s%sbin%sjava", javaHome,
				File.separator, File.separator);
		final String param1 = "-jar";
		final String param3 = "install";
		
		final String folderHome = String.format("%s%sbin", installPath,
				File.separator);

		ProcessBuilder pb = new ProcessBuilder(javaCmd, param1, DAEMON_JAR, param3);
		pb.directory(new File(folderHome));
		pb.start();
	}
}
