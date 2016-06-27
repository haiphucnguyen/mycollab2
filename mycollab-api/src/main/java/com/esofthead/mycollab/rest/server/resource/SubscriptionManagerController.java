package com.esofthead.mycollab.rest.server.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@RestController
@RequestMapping(path = "/subscription")
public class SubscriptionManagerController {
    @RequestMapping(path = "/activate", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String registerEE() {
        return "";
    }
}
