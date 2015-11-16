package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.core.utils.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    public String getStoreWeb() throws IOException {
        File pricingFile = FileUtils.getDesireFile(System.getProperty("user.dir"), "pricing.html", "src/main/conf/pricing.html");
        InputStream pricingStream;
        if (pricingFile != null) {
            pricingStream = new FileInputStream(pricingFile);
        } else {
            pricingStream = CampaignController.this.getClass().getClassLoader().getResourceAsStream("pricing.html");
        }

        return IOUtils.toString(pricingStream, "UTF-8");
    }
}
