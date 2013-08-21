package com.esofthead.mycollab.rest.server.resource;

import org.restlet.resource.Post;

public interface ErrorReportingResource {
	@Post
	void sendErrorTrace(String errorMsg);
}
