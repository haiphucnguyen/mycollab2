package com.esofthead.mycollab.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.spring.SpringRouter;
import org.restlet.routing.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("restApp")
public class RestApp extends Application {
	@Autowired
	protected SpringRouter restRouter;
	
	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
//		// Create a router Restlet that routes each call to a
//		// new instance of HelloWorldResource.
//		Router router = new Router(getContext());
//		// Defines only one route
//		router.attach("/signin", SigninResource.class);
//		return router;
		return restRouter;
	}
}
