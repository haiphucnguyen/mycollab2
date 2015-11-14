package com.esofthead.mycollab.rest.server.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@RestController
public class CampaignController {
    @RequestMapping(method = RequestMethod.GET, path = "/linktobuy")
    public String getLinkToBuy() {
        return "https://www.mycollab.com/contact";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/storeweb")
    public String getStoreWeb() {
        return "";
    }
}
