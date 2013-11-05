package com.esofthead.mycollab.jetty;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;

public abstract class GenericServerRunner {
	private static Logger log = LoggerFactory
			.getLogger(GenericServerRunner.class);

	private Server server;

	public GenericServerRunner() {
		server = new Server(8080);
		log.debug("Detect root folder webapp");
		String webappDirLocation = detectBasedir();
		server.setHandler(buildContext(webappDirLocation));
	}

	public abstract WebAppContext buildContext(String baseDir);

	public void start() throws Exception {
		server.start();
		server.join();
	}

	public void stop() throws Exception {
		server.stop();
	}

	private String detectBasedir() {
		File searchFile = searchFile(new File(System.getProperty("user.dir")),
				"webapp");
		if (searchFile == null) {
			throw new MyCollabException("Can not detect webapp base folder");
		} else {
			log.debug("Webapp base dir is {}", searchFile.getAbsolutePath());
			return searchFile.getAbsolutePath();
		}
	}

	private File searchFile(File homeDir, String foldername) {
		File[] listFiles = homeDir.listFiles();
		for (File file : listFiles) {
			if (file.isDirectory()) {
				if (file.getName().equals(foldername)) {
					return file;
				} else {
					File resultFile = searchFile(file, foldername);
					if (resultFile != null) {
						return resultFile;
					}
				}
			} else {
				continue;
			}
		}

		return null;
	}
}
