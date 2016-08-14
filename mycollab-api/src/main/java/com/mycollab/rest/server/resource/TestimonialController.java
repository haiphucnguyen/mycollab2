package com.mycollab.rest.server.resource;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Ul;
import com.mycollab.common.domain.MailRecipientField;
import com.mycollab.module.mail.service.ExtMailService;
import com.mycollab.module.mail.service.IContentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
@RequestMapping(value = "/testimonial")
public class TestimonialController {

    @Autowired
    private IContentGenerator contentGenerator;

    @Autowired
    private ExtMailService extMailService;

    @RequestMapping(method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
    public void submit(@RequestParam("company") String company, @RequestParam("displayname") String displayname,
                       @RequestParam("jobrole") String jobrole, @RequestParam("email") String email,
                       @RequestParam("website") String website, @RequestParam("testimonial") String testimonial) {
        Div bodyContent = new Div().appendChild(new Ul().appendChild(
                new Li().appendText(String.format("Display name: %s", displayname)),
                new Li().appendText(String.format("Company: %s", company)),
                new Li().appendText(String.format("Role: %s", jobrole)),
                new Li().appendText(String.format("Email: %s", email)),
                new Li().appendText(String.format("Website: %s", website)),
                new Li().appendText(String.format("Testimonial: %s", testimonial))));
        extMailService.sendHTMLMail(email, displayname, Collections.singletonList(new MailRecipientField("hainguyen@mycollab.com", "Hai Nguyen")),
                "New testimonial for you", bodyContent.write());
    }
}
