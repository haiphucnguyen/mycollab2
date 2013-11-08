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

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.FileUtils;

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
		File searchFile = FileUtils.detectFolderByName(
				new File(System.getProperty("user.dir")), "webapp");
		if (searchFile == null) {
			throw new MyCollabException("Can not detect webapp base folder");
		} else {
			return searchFile.getAbsolutePath();
		}
	}
}
