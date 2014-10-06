/**
 * This file is part of mycollab-test.
 *
 * mycollab-test is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-test is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-test.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.test.module.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.esofthead.mycollab.test.TestException;
import com.esofthead.mycollab.test.module.AbstractMyCollabTestModule;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class JettyModule extends AbstractMyCollabTestModule {

	private Server server;

	@Override
	public void setUp() {
		server = new Server(8080);
		WebAppContext context = new WebAppContext();
		context.setDescriptor("src/test/webapp/WEB-INF/web.xml");
		context.setResourceBase("src/test/webapp");
		context.setContextPath("/");
		context.setParentLoaderPriority(true);

		server.setHandler(context);

		try {
			server.start();
		} catch (Exception e) {
			throw new TestException(e);
		}
	}

	@Override
	public void tearDown() {
		if (server != null) {
			try {
				server.stop();
				server.join();
				server.destroy();
				server = null;
			} catch (Exception e) {
				throw new TestException(e);
			}
		}
	}

}
