package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.billing.service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private static Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private BillingService billingService;

    @RequestMapping(value = "signup", method = RequestMethod.POST, headers =
            "Content-Type=application/x-www-form-urlencoded")
    public String signup(@RequestParam("subdomain") String subdomain, @RequestParam("planId") Integer planId,
                         @RequestParam("password") String password, @RequestParam("email") String email,
                         @RequestParam("timezone") String timezoneId, @RequestParam("isEmailVerified") Boolean isEmailVerified) {
        LOG.debug("Register account with subDomain {}, username {}", subdomain, email);
        if (isEmailVerified == null) {
            isEmailVerified = Boolean.FALSE;
        }
        billingService.registerAccount(subdomain, planId, email, password, email, timezoneId, isEmailVerified);

        return SiteConfiguration.getSiteUrl(subdomain);
    }
}
