package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.module.mail.IContentGenerator;
import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.esofthead.mycollab.module.support.domain.Testimonial;
import com.esofthead.mycollab.module.support.service.TestimonialService;
import com.esofthead.mycollab.support.domain.TestimonialForm;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Ul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
public class TestimonialController {
    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private IContentGenerator contentGenerator;

    @Autowired
    private MailRelayService mailRelayService;

    @RequestMapping(value = "/testimonial", method = RequestMethod.POST)
    public void submit(@RequestBody TestimonialForm entity) {
        Testimonial testimonial = new Testimonial();
        testimonial.setCompany(entity.getCompany());
        testimonial.setDisplayname(entity.getDisplayname());
        testimonial.setJobrole(entity.getJobrole());
        testimonial.setTestimonial(entity.getTestimonial());
        testimonial.setWebsite(entity.getWebsite());
        testimonialService.saveWithSession(testimonial, "");

        Div bodyContent = new Div().appendChild(new Ul().appendChild(
                new Li().appendText(String.format("Display name: %s", entity.getDisplayname())),
                new Li().appendText(String.format("Company: %s", entity.getCompany())),
                new Li().appendText(String.format("Role: %s", entity.getJobrole())),
                new Li().appendText(String.format("Email: %s", entity.getEmail())),
                new Li().appendText(String.format("Website: %s", entity.getWebsite())),
                new Li().appendText(String.format("Testimonial: %s", entity.getTestimonial()))));
        mailRelayService.saveRelayEmail(new String[]{"Sir"},
                new String[]{"hainguyen@esofthead.com"}, contentGenerator
                        .parseString("New testimonial for you"), bodyContent.write());
    }
}
