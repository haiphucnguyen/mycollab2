package com.mycollab.rest.server.resource;

import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.ondemand.module.billing.service.BillingService;
import com.mycollab.ondemand.module.support.dao.EmailReferenceMapper;
import com.mycollab.ondemand.module.support.domain.EmailReference;
import com.mycollab.ondemand.module.support.domain.EmailReferenceExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.GregorianCalendar;

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

    @Autowired
    private EmailReferenceMapper emailReferenceMapper;

    @RequestMapping(value = "signup", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
    public String signup(@RequestParam("subdomain") String subdomain, @RequestParam("planId") Integer planId,
                         @RequestParam("password") String password, @RequestParam("email") String email,
                         @RequestParam("timezone") String timezoneId, @RequestParam("isEmailVerified") Boolean isEmailVerified) {
        LOG.debug("Register account with subDomain {}, username {}", subdomain, email);
        if (isEmailVerified == null) {
            isEmailVerified = Boolean.FALSE;
        }
        billingService.registerAccount(subdomain, planId, email, password, email, timezoneId, isEmailVerified);

        EmailReferenceExample ex = new EmailReferenceExample();
        ex.createCriteria().andEmailEqualTo(email);
        if (emailReferenceMapper.countByExample(ex) == 0) {
            EmailReference emailReference = new EmailReference();
            emailReference.setCreatedtime(new GregorianCalendar().getTime());
            emailReference.setEmail(email);
            emailReference.setSubscribe(Boolean.TRUE);
            emailReferenceMapper.insert(emailReference);
        }
        return SiteConfiguration.getSiteUrl(subdomain);
    }
}
