package com.esofthead.mycollab.jetty;

import org.eclipse.jetty.webapp.WebAppContext;

public class CommunityServerRunner extends GenericServerRunner {
	@Override
	public WebAppContext buildContext(String baseDir) {
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setWar(baseDir);
		webAppContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		webAppContext.setResourceBase(baseDir);
		return webAppContext;
	}

	public static void main(String[] args) throws Exception {
		new CommunityServerRunner().start();
	}
}
