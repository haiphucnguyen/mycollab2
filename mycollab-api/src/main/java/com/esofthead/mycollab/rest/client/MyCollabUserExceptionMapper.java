package com.esofthead.mycollab.rest.client;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.esofthead.mycollab.core.UserInvalidInputException;

public class MyCollabUserExceptionMapper implements
		ExceptionMapper<UserInvalidInputException> {

	@Override
	public Response toResponse(UserInvalidInputException exception) {
		return Response.status(400).build();
	}

}
