package com.esofthead.mycollab.rest.server;

import org.restlet.Application;

public class RestApp extends Application {
	public RestApp() {
		this.setStatusService(new StatusServiceEx());
	}
}
