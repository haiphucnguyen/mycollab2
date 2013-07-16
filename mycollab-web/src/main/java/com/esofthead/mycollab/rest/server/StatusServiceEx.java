package com.esofthead.mycollab.rest.server;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.service.StatusService;

import com.esofthead.mycollab.core.MyCollabException;

public class StatusServiceEx extends StatusService {

	@Override
	public Representation getRepresentation(Status status, Request request,
			Response response) {
		return response.getEntity();
	}

	@Override
	public Status getStatus(Throwable throwable, Request request,
			Response response) {
		if (throwable instanceof ResourceException) {
			Throwable cause = ((ResourceException) throwable).getCause();

			if (cause instanceof MyCollabException) {
				response.setEntity(new StringRepresentation(cause.getMessage()));
				return Status.SERVER_ERROR_INTERNAL;
			}
		}
		// Otherwise use standard handling.
		return super.getStatus(throwable, request, response);
	}

}
