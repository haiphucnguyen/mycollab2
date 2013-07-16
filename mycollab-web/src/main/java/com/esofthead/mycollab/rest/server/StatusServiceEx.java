package com.esofthead.mycollab.rest.server;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.service.StatusService;

public class StatusServiceEx extends StatusService {

	@Override
	public Representation getRepresentation(Status status, Request request,
			Response response) {
		if (status.getThrowable() != null) {
			return new StringRepresentation(status.getThrowable().getMessage());
		}
		return super.getRepresentation(status, request, response);
	}

}
