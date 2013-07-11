package com.esofthead.mycollab.rest.server.resource;

import org.restlet.data.Form;
import org.restlet.resource.Post;

public interface UserHubResource {
	@Post("form")
	public String doPost(Form entity);
}
