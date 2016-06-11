package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabVersion;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.mail.service.IContentGenerator;
import com.esofthead.mycollab.ondemand.module.support.dao.CommunityLeadMapper;
import com.esofthead.mycollab.ondemand.module.support.domain.CommunityLead;
import com.esofthead.mycollab.ondemand.module.support.domain.CommunityLeadExample;
import com.esofthead.mycollab.ondemand.module.support.domain.EditionInfo;
import com.esofthead.mycollab.ondemand.module.support.service.EditionInfoResolver;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@RestController
public class CampaignController {
    @Autowired
    private CommunityLeadMapper communityLeadMapper;

    @Autowired
    private EditionInfoResolver editionInfoResolver;

    @RequestMapping(method = RequestMethod.GET, path = "/linktobuy")
    public String getLinkToBuy() throws IOException {
        return FileUtils.readFileAsPlainString("buying.html");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/storeweb")
    public String getStoreWeb() throws IOException {
        return FileUtils.readFileAsPlainString("pricing.html");
    }

    @Autowired
    private IContentGenerator contentGenerator;

    @Autowired
    private ExtMailService extMailService;

    @RequestMapping(path = "/register-ce", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public Map registerCE(@RequestParam("firstname") final String firstname, @RequestParam("lastname") final String lastname,
                          @RequestParam("email") final String email, @RequestParam("role") final String role,
                          @RequestParam("company") final String company, @RequestParam("phone") final String phone,
                          @RequestParam("country") final String country, @RequestParam("edition") final String edition) {
        final EditionInfo info = editionInfoResolver.getEditionInfo();

        new Thread() {
            @Override
            public void run() {
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
                communityLead.setEdition(edition);

                CommunityLeadExample ex = new CommunityLeadExample();
                ex.createCriteria().andFirstnameEqualTo(firstname).andLastnameEqualTo(lastname).andEmailEqualTo(email)
                        .andVersionEqualTo(MyCollabVersion.getVersion());
                if (communityLeadMapper.countByExample(ex) == 0) {
                    communityLeadMapper.insert(communityLead);
                }

                contentGenerator.putVariable("lastname", lastname);
                contentGenerator.putVariable("version", info.getVersion());
                if ("Ultimate".equals(edition)) {
                    contentGenerator.putVariable("downloadLink", String.format("https://api.mycollab" +
                            ".con/api/download/verify?username=%s&&edition=Ultimate", email));
                } else {
                    contentGenerator.putVariable("downloadLink", String.format("https://api.mycollab" +
                            ".con/api/download/verify?username=%s", email));
                }

                extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getDefaultSiteName(),
                        Arrays.asList(new MailRecipientField(email, firstname + " " + lastname)), null, null, "MyCollab is " +
                                "ready for download", contentGenerator.parseFile("mailDownloadInfo.html", Locale.US),
                        null);
            }
        }.start();

        Map<String, String> result = new HashMap<>();
        String name = String.format("MyCollab-All-%s.zip", info.getVersion());
        String link = info.getCommunityDownloadLink();
        String altLink = info.getAltCommunityDownloadLink();
        result.put("name", name);
        result.put("link", link);
        result.put("altlink", altLink);
        return result;
    }
}
