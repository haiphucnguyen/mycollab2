package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.support.domain.TestimonialForm;
import org.jboss.resteasy.annotations.Form;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author MyCollab Ltd.
 * @since 5.0.6
 */
@Path("/testimonial")
public interface TestimonialResource {
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/submit")
    Response submit(@Form TestimonialForm entity);
}
