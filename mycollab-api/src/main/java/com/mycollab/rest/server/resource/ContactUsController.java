package com.mycollab.rest.server.resource;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Ul;
import com.mycollab.common.domain.MailRecipientField;
import com.mycollab.module.mail.service.ExtMailService;
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
@RequestMapping(value = "/contact-us")
public class ContactUsController {

    @Autowired
    private ExtMailService extMailService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST, headers = {"Content-Type=application/x-www-form-urlencoded"})
    public String doContact(@RequestParam("name") String name, @RequestParam("email") String email,
                            @RequestParam("company") String company, @RequestParam("role") String role,
                            @RequestParam("industry") String industry, @RequestParam("budget") String budget,
                            @RequestParam("subject") String subject, @RequestParam("message") String message) {
        Div bodyContent = new Div().appendChild(new Ul().appendChild(
                new Li().appendText(String.format("Name: %s", name)),
                new Li().appendText(String.format("Email: %s", email)),
                new Li().appendText(String.format("Company: %s", company)),
                new Li().appendText(String.format("Role: %s", role)),
                new Li().appendText(String.format("Industry: %s", industry)),
                new Li().appendText(String.format("Budget: %s", budget)),
                new Li().appendText(String.format("Subject: %s", subject)),
                new Li().appendText(String.format("Message: %s", message))));
        extMailService.sendHTMLMail(email, name, Collections.singletonList(new MailRecipientField("hainguyen@mycollab.com",
                "Hai Nguyen")), "MyCollab inquiry", bodyContent.write());
        return "Ok";
    }
}
