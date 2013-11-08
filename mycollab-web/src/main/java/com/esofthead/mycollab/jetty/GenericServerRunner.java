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
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ShutdownHandler;
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
		server = new Server(SiteConfiguration.getServerPort());
		log.debug("Detect root folder webapp");
		String webappDirLocation = detectBasedir();

		WebAppContext appContext = buildContext(webappDirLocation);
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { appContext,
				new ShutdownHandler(server, "esoftheadsecretkey") });
		server.setHandler(handlers);
	}

	public static void attemptShutdown(int port, String shutdownCookie) {
		try {
			URL url = new URL("http://localhost:" + port + "/shutdown?token="
					+ shutdownCookie);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.getResponseCode();
			log.info("Shutting down " + url + ": "
					+ connection.getResponseMessage());
		} catch (SocketException e) {
			log.debug("Not running");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
}
