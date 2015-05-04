package com.esofthead.mycollab.rest.server.resource.impl;

import com.esofthead.mycollab.module.mail.IContentGenerator;
import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.esofthead.mycollab.module.support.domain.Testimonial;
import com.esofthead.mycollab.module.support.service.TestimonialService;
import com.esofthead.mycollab.support.domain.TestimonialForm;
import com.esofthead.mycollab.rest.server.resource.TestimonialResource;
import org.jboss.resteasy.annotations.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
public class TestimonialResourceImpl implements TestimonialResource {
    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private IContentGenerator contentGenerator;

    @Autowired
    private MailRelayService mailRelayService;

    @Override
    public Response submit(@Form TestimonialForm entity) {
        Testimonial testimonial = new Testimonial();
        testimonial.setCompany(entity.getCompany());
        testimonial.setDisplayname(entity.getDisplayname());
        testimonial.setJobrole(entity.getJobrole());
        testimonial.setTestimonial(entity.getTestimonial());
        testimonial.setWebsite(entity.getWebsite());
        testimonialService.saveWithSession(testimonial, "");

        mailRelayService.saveRelayEmail(new String[]{"Sir"},
                new String[]{"hainguyen@esofthead.com"}, contentGenerator
                        .generateSubjectContent("New testimonial for you"),
                "You have a new testimonial");

        Response response = Response.status(200).entity("OK")
                .type(MediaType.TEXT_PLAIN_TYPE).build();
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        return response;
    }
}
