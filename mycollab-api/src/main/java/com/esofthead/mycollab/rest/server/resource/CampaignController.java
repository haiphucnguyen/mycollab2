package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.core.MyCollabVersion;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.ondemand.module.support.dao.CommunityLeadMapper;
import com.esofthead.mycollab.ondemand.module.support.domain.CommunityLead;
import com.esofthead.mycollab.ondemand.module.support.domain.CommunityLeadExample;
import org.apache.commons.io.IOUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private CommunityLeadMapper communityLeadMapper;

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

    @RequestMapping(path = "/register-ce", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String registerCE(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
                             @RequestParam("email") String email, @RequestParam("role") String role,
                             @RequestParam("company") String company, @RequestParam("phone") String phone,
                             @RequestParam("country") String country) {
        CommunityLead communityLead = new CommunityLead();
        communityLead.setFirstname(firstname);
        communityLead.setLastname(lastname);
        communityLead.setEmail(email);
        communityLead.setCompany(company);
        communityLead.setRole(role);
        communityLead.setPhone(phone);
        communityLead.setCountry(country);
        communityLead.setRegisterdate(new LocalDate().toDate());
        communityLead.setVersion(MyCollabVersion.getVersion());
        communityLead.setEdition("Community");

        CommunityLeadExample ex = new CommunityLeadExample();
        ex.createCriteria().andFirstnameEqualTo(firstname).andLastnameEqualTo(lastname).andEmailEqualTo(email)
                .andVersionEqualTo(MyCollabVersion.getVersion());
        if (communityLeadMapper.countByExample(ex) == 0) {
            communityLeadMapper.insert(communityLead);
        }

        return String.format("https://sourceforge.net/projects/mycollab/files/MyCollab_%s/MyCollab-%s-All.zip/download",
                MyCollabVersion.getVersion(), MyCollabVersion.getVersion());
    }
}
