package com.esofthead.mycollab.rest.server.resource.impl;

import java.util.List;

import org.restlet.Server;
import org.restlet.data.Form;
import org.restlet.data.Protocol;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.rest.server.resource.UserHubResource;

@Component("restUserResource")
public class UserHubResourceImpl extends ServerResource implements
		UserHubResource {
	private static Logger log = LoggerFactory
			.getLogger(UserHubResourceImpl.class);

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
		billingService.registerAccount(subdomain, planId, username, password,
				email, timezoneId);
		return "ok";
	}

	public static void main(String[] args) throws Exception {
		// Create the HTTP server and listen on port 8182
		Server server = new Server(Protocol.HTTP, 8182,
				UserHubResourceImpl.class);
		server.start();
	}

	@Override
	public List<String> getSubdomainsOfUser(String username) {
		return billingService.getSubdomainsOfUser(username);
	}
}
