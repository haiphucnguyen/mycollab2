package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.module.mail.service.IContentGenerator;
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
@RequestMapping(value = "/testimonial")
public class TestimonialController {

    @Autowired
    private IContentGenerator contentGenerator;

    @Autowired
    private MailRelayService mailRelayService;

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
        mailRelayService.saveRelayEmail(new String[]{"Sir"},
                new String[]{"hainguyen@mycollab.com"}, contentGenerator
                        .parseString("New testimonial for you"), bodyContent.write());
    }
}
