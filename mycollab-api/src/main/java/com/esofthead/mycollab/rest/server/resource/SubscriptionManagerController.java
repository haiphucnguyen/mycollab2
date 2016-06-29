package com.esofthead.mycollab.rest.server.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@RestController
@RequestMapping(path = "/subscription")
public class SubscriptionManagerController {
    private static Logger LOG = LoggerFactory.getLogger(SubscriptionManagerController.class);

    @RequestMapping(path = "/register", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String registerEE(@RequestParam("company") String company, @RequestParam("email") String email,
                             @RequestParam("internalProductName") String internalProductName,
                             @RequestParam("name") String name, @RequestParam("quantity") int quantity,
                             @RequestParam("reference") String reference,
                             @RequestParam("subscriptionReference") String subscriptionReference,
                             @RequestParam("test") String test, @RequestParam("security_request_hash") String security_request_hash) {
        LOG.info("Subscription reference: " + reference+ "- Sub " + subscriptionReference);
        return "Ok";
    }

    @RequestMapping(path = "/activated", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String activateSubscription(@RequestParam("SubscriptionCustomerUrl") String subscriptionCustomerUrl,
                                       @RequestParam("SubscriptionEndDate") String subscriptionEndDate,
                                       @RequestParam("SubscriptionIsTest") String subscriptionIsTest,
                                       @RequestParam("SubscriptionQuantity") String subscriptionQuantity,
                                       @RequestParam("SubscriptionReference") String subscriptionReference,
                                       @RequestParam("SubscriptionReferrer") String subscriptionReferrer) {
        LOG.info("Subscription reference: " + subscriptionReference);
        return "Ok";
    }
}
