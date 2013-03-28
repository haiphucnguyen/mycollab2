package com.esofthead.mycollab.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.spring.SpringRouter;
import org.restlet.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("restApp")
public class RestApp extends Application {
	@Autowired
	protected SpringRouter restRouter;

	public RestApp() {
		this.setStatusService(new GenericStatusService());
	}

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		return restRouter;
	}

	private static class GenericStatusService extends StatusService {

	}
}
