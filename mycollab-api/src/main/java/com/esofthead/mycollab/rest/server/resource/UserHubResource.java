package com.esofthead.mycollab.rest.server.resource;

import org.restlet.data.Form;
import org.restlet.resource.Post;

public interface UserHubResource {
	@Post("form")
	String signup(Form entity);

	@Post
	String[] getSubdomainsOfUser(String username);
}
