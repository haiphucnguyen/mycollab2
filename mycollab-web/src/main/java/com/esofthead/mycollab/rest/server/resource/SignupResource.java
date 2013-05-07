package com.esofthead.mycollab.rest.server.resource;

import org.json.JSONException;
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

@Component("restUserResource")
public class SignupResource extends ServerResource {
	private static Logger log = LoggerFactory.getLogger(SignupResource.class);

	@Autowired
	BillingService billingService;

	@Post("form")
	public String doPost(Form form) throws JSONException {
		log.debug("Start handling form request");
		String subdomain = form.getFirstValue("subdomain");
		String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");
		String email = form.getFirstValue("email");
		String timezoneId = form.getFirstValue("timezone");
		int planId = Integer.parseInt(form.getFirstValue("planId"));
		billingService.registerAccount(subdomain, planId, username, password,
				email, timezoneId);
		return "aaa";
	}

	public static void main(String[] args) throws Exception {
		// Create the HTTP server and listen on port 8182
		Server server = new Server(Protocol.HTTP, 8182, SignupResource.class);
		server.start();
	}
}
