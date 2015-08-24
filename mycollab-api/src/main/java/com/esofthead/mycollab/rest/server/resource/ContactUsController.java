package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Ul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
@RequestMapping(value = "/contact-us")
public class ContactUsController {

    @Autowired
    private MailRelayService mailRelayService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
    public void doContact(@RequestParam("name") String name, @RequestParam("email") String email,
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
        mailRelayService.saveRelayEmail(new String[]{"Sir"},
                new String[]{"hainguyen@mycollab.com"}, "New guy want to contact", bodyContent.write());
    }
}
