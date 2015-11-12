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
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String getLatestVersion() {
        return "";
    }
}
