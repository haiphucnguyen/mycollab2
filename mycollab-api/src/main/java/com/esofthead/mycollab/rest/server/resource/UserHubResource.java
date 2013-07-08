package com.esofthead.mycollab.rest.server.resource;

import java.util.List;

import org.restlet.resource.Post;

public interface UserHubResource {
	@Post
	public String doPost(String subdomain, String username, String password,
			String email, int planId, String firstname, String lastname,
			String timezoneId) throws SubdomainExistedException;

	List<String> getSubdomainsOfUser(String username);
}
