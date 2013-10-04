package com.esofthead.mycollab.rest.server.resource;

import javax.ws.rs.POST;

import org.jboss.resteasy.annotations.Form;

import com.esofthead.mycollab.rest.server.domain.SignupForm;

public interface AccountResource {
	@POST
	String signup(@Form SignupForm entity);
}
