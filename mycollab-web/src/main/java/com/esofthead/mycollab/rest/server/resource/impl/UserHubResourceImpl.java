package com.esofthead.mycollab.rest.server.resource.impl;

import java.util.List;

import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.rest.server.resource.UserHubResource;
import com.esofthead.mycollab.rest.server.signup.ExistingEmailRegisterException;
import com.esofthead.mycollab.rest.server.signup.ExistingUserRegisterException;
import com.esofthead.mycollab.rest.server.signup.SubdomainExistedException;

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
	public List<String> getSubdomainsOfUser(final String username) {
		return this.billingService.getSubdomainsOfUser(username);
	}

	@Override
	@Post
	public String doPost(final String subdomain, final String username,
			final String password, final String email, final int planId,
			final String firstname, final String lastname,
			final String timezoneId) throws SubdomainExistedException,
			ExistingEmailRegisterException, ExistingUserRegisterException {
		UserHubResourceImpl.log.debug("Start handling form request");
		this.billingService.registerAccount(subdomain, planId, username,
				password, email, timezoneId);
		String siteUrl = "";
		if (ApplicationProperties.productionMode) {
			siteUrl = String.format(ApplicationProperties
					.getString(ApplicationProperties.APP_URL), subdomain);
		} else {
			final boolean isSupportSubDomain = ApplicationProperties
					.getBoolean(ApplicationProperties.SUPPORT_ACCOUNT_SUBDOMAIN);
			if (!isSupportSubDomain) {
				siteUrl = String.format(ApplicationProperties
						.getString(ApplicationProperties.APP_URL),
						ApplicationProperties
								.getString(ApplicationProperties.SITE_NAME));
			} else {
				siteUrl = String.format(ApplicationProperties
						.getString(ApplicationProperties.APP_URL), subdomain);
			}
		}
		return siteUrl;
	}
}
