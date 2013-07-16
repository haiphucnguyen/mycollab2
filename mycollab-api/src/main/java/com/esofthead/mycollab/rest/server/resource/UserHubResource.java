package com.esofthead.mycollab.rest.server.resource;

import java.util.List;

import org.restlet.data.Form;
import org.restlet.resource.Post;

public interface UserHubResource {
	@Post("form")
	String signup(Form entity);

	@Post
	List<String> getSubdomainsOfUser(String username);
}
