import java.io.File;
import java.util.List;

import com.izforge.izpack.event.SimpleUninstallerListener;
import com.izforge.izpack.util.AbstractUIProgressHandler;

public class MyCollabUninstallerListener extends SimpleUninstallerListener {
	static final String DAEMON_JAR = "mycollab-daemon.jar";
	
	@Override
	public void beforeDeletion(List arg0, AbstractUIProgressHandler arg1)
			throws Exception {
		final String currentLocation = System.getProperty("user.dir");
		final String installPath = new File(currentLocation).getParent();
		final String javaHome = System.getProperty("java.home");
		final String javaCmd = String.format("%s%sbin%sjava", javaHome,
				File.separator, File.separator);
		final String param1 = "-jar";
		final String param3 = "uninstall";
		
		final String folderHome = String.format("%s%sbin", installPath,
				File.separator);
		
		ProcessBuilder pb = new ProcessBuilder(javaCmd, param1, DAEMON_JAR, param3);
		pb.directory(new File(folderHome));
		Process p = pb.start();
		while (true) {
			try {
				Thread.sleep(5000);
				p.destroy();
				break;
			} catch (Exception ex) {
				
			}
		}
		
		p = null;
		pb = null;
	}
}
