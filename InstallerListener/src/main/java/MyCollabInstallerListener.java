import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;

import com.izforge.izpack.PackFile;
import com.izforge.izpack.event.SimpleInstallerListener;
import com.izforge.izpack.installer.AutomatedInstallData;
import com.izforge.izpack.util.AbstractUIProgressHandler;
import com.izforge.izpack.util.FileExecutor;
import com.izforge.izpack.util.OsVersion;

public class MyCollabInstallerListener extends SimpleInstallerListener {
	static final String DAEMON_JAR = "mycollab-daemon.jar";

	public boolean isFileListener() {
		return true;
	}

	@Override
	public void afterPacks(AutomatedInstallData arg0,
			AbstractUIProgressHandler arg1) throws Exception {
		super.afterPacks(arg0, arg1);

		final String installPath = arg0.getInstallPath();
		final String javaHome = System.getProperty("java.home");
		final String javaCmd = String.format("%s%sbin%sjava", javaHome,
				File.separator, File.separator);
		final String param1 = "-jar";
		final String param3 = "install";

		final String folderHome = String.format("%s%sbin", installPath,
				File.separator);

		final ProcessBuilder pb = new ProcessBuilder(javaCmd, param1, DAEMON_JAR,
				param3);
		pb.directory(new File(folderHome));
		
		AccessController.doPrivileged(new PrivilegedAction<Object>() {
			public Object run() {
				try {
					pb.start();
				} catch (Exception ex) {
					
				}
				return null;
			}
		});	
	}

	@Override
	public void afterFile(File filePath, PackFile pf) throws Exception {
		super.afterFile(filePath, pf);

		if (OsVersion.IS_WINDOWS)
			return;

		final String path = filePath.getAbsolutePath();
		if (path.endsWith(".sh")) {
			
			AccessController.doPrivileged(new PrivilegedAction<Object>() {
				public Object run() {
					try {
						chmod(path, "+x");
					} catch (Exception ex) {
						
					}
					return null;
				}
			});		
			
		}
	}

	private final void chmod(String filePath, String param) throws Exception {
		final String cmd = "chmod";
		String[] params = new String[] { cmd, param, filePath };
		String[] output = new String[2];
		FileExecutor fe = new FileExecutor();
		fe.executeCommand(params, output);
	}

}
