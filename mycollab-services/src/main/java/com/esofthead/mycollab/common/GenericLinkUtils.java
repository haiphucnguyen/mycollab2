package com.esofthead.mycollab.common;

import com.esofthead.mycollab.configuration.ApplicationProperties;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public abstract class GenericLinkUtils {
	public static String URL_PREFIX_PARAM = "#";

	public static String encodeParam(Object... params) {
		StringBuffer paramStr = new StringBuffer("");
		for (int i = 0; i < params.length; i++) {
			paramStr.append(params[i].toString());
			if (i != params.length - 1) {
				paramStr.append("/");
			}
		}
		return UrlEncodeDecoder.encode(paramStr.toString());
	}

	public String generateUserPreviewFullLink(String username) {
		return getSiteUrl() + GenericLinkUtils.URL_PREFIX_PARAM
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
