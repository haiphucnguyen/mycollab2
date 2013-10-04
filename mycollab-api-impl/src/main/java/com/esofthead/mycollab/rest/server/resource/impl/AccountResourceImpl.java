package com.esofthead.mycollab.rest.server.resource.impl;

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.rest.server.domain.SignupForm;
import com.esofthead.mycollab.rest.server.resource.AccountResource;

@Component
public class AccountResourceImpl implements AccountResource {
	private static Logger log = LoggerFactory
			.getLogger(AccountResourceImpl.class);

	@Autowired
	private BillingService billingService;

	@Override
	public String signup(@Form SignupForm entity) {
		log.debug("Start handling form request");
		String subdomain = entity.getSubdomain();
		int planId = entity.getPlanId();
		String password = entity.getPassword();
		String email = entity.getEmail();
		String timezoneId = entity.getTimezoneId();

		log.debug("Register account with subdomain {}, username {}", subdomain,
				email);
		this.billingService.registerAccount(subdomain, planId, email, password,
				email, timezoneId);

		String siteUrl = SiteConfiguration.getSiteUrl(subdomain);
		log.debug("Return site url {} to sign up user {}", siteUrl, email);
		return siteUrl;
	}

}
