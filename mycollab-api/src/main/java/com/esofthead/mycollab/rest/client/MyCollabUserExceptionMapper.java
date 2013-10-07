package com.esofthead.mycollab.rest.client;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.UserInvalidInputException;

@Provider
@Component
public class MyCollabUserExceptionMapper implements
		ExceptionMapper<UserInvalidInputException> {

	@Override
	public Response toResponse(UserInvalidInputException exception) {
		return Response.status(400).entity(exception.getMessage()).build();
	}

}
