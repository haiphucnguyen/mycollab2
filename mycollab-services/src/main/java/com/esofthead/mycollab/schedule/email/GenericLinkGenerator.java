package com.esofthead.mycollab.schedule.email;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.ApplicationProperties;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public abstract class GenericLinkGenerator {

	public String generateUserPreviewFullLink(String username) {
		return getSiteUrl() + ProjectLinkUtils.URL_PREFIX_PARAM
				+ "account/user/preview/" + UrlEncodeDecoder.encode(username);
	}

	public static String generateSiteUrlByAccountId(Integer sAccountId) {
		String siteUrl = "";
		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			BillingAccountService billingAccountService = ApplicationContextUtil
					.getSpringBean(BillingAccountService.class);
			BillingAccount account = billingAccountService
					.getAccountById(sAccountId);
			if (account != null) {
				siteUrl = String.format(ApplicationProperties
						.getString(ApplicationProperties.APP_URL), account
						.getSubdomain());
			}
		} else {
			siteUrl = ApplicationProperties
					.getString(ApplicationProperties.APP_URL);
		}
		return siteUrl;
	}

	abstract public String getSiteUrl();
}
