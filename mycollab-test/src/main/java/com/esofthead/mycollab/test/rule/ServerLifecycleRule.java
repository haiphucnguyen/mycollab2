package com.esofthead.mycollab.test.rule;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.esofthead.mycollab.test.TestException;
import com.esofthead.mycollab.test.WebServer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class ServerLifecycleRule implements TestRule {

	@Override
	public Statement apply(Statement base, Description description) {
		if (description.getAnnotation(WebServer.class) != null) {
			return new WrappedStatement(base);
		} else {
			return base;
		}
	}

	private class WrappedStatement extends Statement {
		private Server server;
		private Statement baseStm;

		WrappedStatement(Statement base) {
			this.baseStm = base;
		}

		private void setUp() {
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

		private void tearDown() {
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

		@Override
		public void evaluate() throws Throwable {
			setUp();
			baseStm.evaluate();
			tearDown();

		}
	}

}
