package com.esofthead.mycollab.rest.server.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Form;

import com.esofthead.mycollab.rest.server.domain.ContactForm;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 *
 */
@Path("/contact-us")
public interface ContactResource {
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/submit")
	Response submit(@Form ContactForm entity);

}
