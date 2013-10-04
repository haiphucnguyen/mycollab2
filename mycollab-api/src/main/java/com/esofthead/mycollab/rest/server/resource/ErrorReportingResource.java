package com.esofthead.mycollab.rest.server.resource;

import javax.ws.rs.POST;

public interface ErrorReportingResource {
	@POST
	void sendErrorTrace(String errorMsg);
}
