/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.jetty;

import java.io.File;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.FileUtils;

public abstract class GenericServerRunner {
	private static Logger log = LoggerFactory
			.getLogger(GenericServerRunner.class);

	private Server server;

	public GenericServerRunner() {

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
		File searchFile = FileUtils.detectFolderByName(
				new File(System.getProperty("user.dir")), "webapp");
		if (searchFile == null) {
			throw new MyCollabException("Can not detect webapp base folder");
		} else {
			return searchFile.getAbsolutePath();
		}
	}

	public void run(String[] args) throws Exception {
		int stopPort = 0;
		String stopKey = null;
		boolean isStop = false;

		for (int i = 0; i < args.length; i++) {
			if ("--stop-port".equals(args[i])) {
				stopPort = Integer.parseInt(args[++i]);
			} else if ("--stop-key".equals(args[i])) {
				stopKey = args[++i];
			} else if ("--stop".equals(args[i])) {
				isStop = true;
			}
		}

		switch ((stopPort > 0 ? 1 : 0) + (stopKey != null ? 2 : 0)) {
		case 1:
			usage("Must specify --stop-key when --stop-port is specified");
			break;

		case 2:
			usage("Must specify --stop-port when --stop-key is specified");
			break;

		case 3:
			if (isStop) {
				Socket s = new Socket(InetAddress.getByName("localhost"),
						stopPort);
				try {
					OutputStream out = s.getOutputStream();
					out.write((stopKey + "\r\nstop\r\n").getBytes());
					out.flush();
				} finally {
					s.close();
				}
				return;
			} else {
				ShutdownMonitor monitor = ShutdownMonitor.getInstance();
				monitor.setPort(stopPort);
				monitor.setKey(stopKey);
				monitor.setExitVm(true);
				execute();
				break;
			}

		}

	}

	public void execute() throws Exception {
		server = new Server(SiteConfiguration.getServerPort());
		log.debug("Detect root folder webapp");
		String webappDirLocation = detectBasedir();

		WebAppContext appContext = buildContext(webappDirLocation);
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { appContext });
		server.setHandler(handlers);

		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		server.start();
		
		ShutdownMonitor.getInstance().start();
		
		server.join();
	}

	public void usage(String error) {
		if (error != null)
			System.err.println("ERROR: " + error);
		System.err
				.println("Usage: java -jar runner.jar [--help|--version] [ server opts]");
		System.err.println("Server Options:");
		System.err
				.println(" --version                          - display version and exit");
		System.err
				.println(" --stop-port n                      - port to listen for stop command");
		System.err
				.println(" --stop-key n                       - security string for stop command (required if --stop-port is present)");
		System.exit(1);
	}
}
