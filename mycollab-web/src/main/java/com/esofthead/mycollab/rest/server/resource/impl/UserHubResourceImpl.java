package com.esofthead.mycollab.rest.server.resource.impl;

import java.util.List;

import org.restlet.Server;
import org.restlet.data.Form;
import org.restlet.data.Protocol;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.rest.server.resource.UserHubResource;

@Component("restUserResource")
public class UserHubResourceImpl extends ServerResource implements
		UserHubResource {
	private static Logger log = LoggerFactory
			.getLogger(UserHubResourceImpl.class);

	@Autowired
	private BillingService billingService;

	public static void main(final String[] args) throws Exception {
		// Create the HTTP server and listen on port 8182
		final Server server = new Server(Protocol.HTTP, 8182,
				UserHubResourceImpl.class);
		server.start();
	}

	@Override
	@Post
	public String[] getSubdomainsOfUser(final String username) {
		List<String> subdomains = this.billingService
				.getSubdomainsOfUser(username);
		if (subdomains != null) {
			log.debug("There are subdomains for user {} {}", username, BeanUtility.printBeanObj(subdomains));
			return subdomains.toArray(new String[0]);
		} else {
			log.debug("There is no subdomain for user {}", username);
			return new String[0];
		}
	}

	@Override
	@Post("form")
	public String signup(Form form) {
		UserHubResourceImpl.log.debug("Start handling form request");
		String subdomain = form.getFirstValue("subdomain");
		int planId = Integer.parseInt(form.getFirstValue("planId"));
		String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");
		String email = form.getFirstValue("email");
		String timezoneId = form.getFirstValue("timezoneId");
		try {
			this.billingService.registerAccount(subdomain, planId, username,
					password, email, timezoneId);
		} catch (MyCollabException e) {
			throw new ResourceException(e);
		}
		String siteUrl = ApplicationProperties.getSiteUrl(subdomain);
		return siteUrl;
	}
}
