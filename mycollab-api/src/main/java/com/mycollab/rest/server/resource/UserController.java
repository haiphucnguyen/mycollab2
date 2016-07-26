package com.mycollab.rest.server.resource;

import com.mycollab.core.utils.BeanUtility;
import com.mycollab.ondemand.module.billing.service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BillingService billingService;

    @RequestMapping(value = "/subdomains/{username:.+}", method = RequestMethod.GET)
    public String[] getSubdomainsOfUser(@PathVariable("username") String username) {
        List<String> subdomains = billingService.getSubDomainsOfUser(username);
        String[] result;
        if (subdomains != null && subdomains.size() > 0) {
            result = subdomains.toArray(new String[subdomains.size()]);
            LOG.debug("There are subdomains for user {} {}", username, BeanUtility.printBeanObj(result));

        } else {
            LOG.debug("There is no subDomain for user {}", username);
            result = new String[0];
        }
        return result;
    }
}
