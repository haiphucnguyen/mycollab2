package com.esofthead.mycollab.rest.server.resource.impl;

import org.restlet.Server;
import org.restlet.data.Form;
import org.restlet.data.Protocol;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.rest.server.resource.SignupResource;

@Component("restUserResource")
public class SignupResourceImpl extends ServerResource implements
		SignupResource {
	private static Logger log = LoggerFactory
			.getLogger(SignupResourceImpl.class);

	@Autowired
	private BillingService billingService;

	@Post("form")
	public String doPost(Form form) {
		log.debug("Start handling form request");
		String subdomain = form.getFirstValue("subdomain");
		String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");
		String email = form.getFirstValue("email");
		String timezoneId = form.getFirstValue("timezone");
		int planId = Integer.parseInt(form.getFirstValue("planId"));
		// billingService.registerAccount(subdomain, planId, username, password,
		// email, timezoneId);
		throw new MyCollabException("aaa");
	}

	public static void main(String[] args) throws Exception {
		// Create the HTTP server and listen on port 8182
		Server server = new Server(Protocol.HTTP, 8182,
				SignupResourceImpl.class);
		server.start();
	}
}
