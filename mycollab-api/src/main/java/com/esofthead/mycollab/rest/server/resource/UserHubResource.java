package com.esofthead.mycollab.rest.server.resource;

import java.util.List;

import org.restlet.resource.Post;

import com.esofthead.mycollab.rest.server.signup.ExistingEmailRegisterException;
import com.esofthead.mycollab.rest.server.signup.ExistingUserRegisterException;
import com.esofthead.mycollab.rest.server.signup.SubdomainExistedException;

public interface UserHubResource {
	@Post
	public String doPost(String subdomain, String username, String password,
			String email, int planId, String firstname, String lastname,
			String timezoneId) throws SubdomainExistedException,
			ExistingEmailRegisterException, ExistingUserRegisterException;

	List<String> getSubdomainsOfUser(String username);
}
