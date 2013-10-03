package com.esofthead.mycollab.rest.server;

import org.restlet.Application;
import org.restlet.engine.log.LogFilter;

public class RestApp extends Application {
	public RestApp() {
		LogFilter a;
		this.setStatusService(new StatusServiceEx());
	}
}
