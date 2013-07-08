package com.esofthead.mycollab.rest.server.resource;

import org.restlet.data.Form;
import org.restlet.resource.Post;

import com.esofthead.mycollab.rest.server.signup.ExistingEmailRegisterException;
import com.esofthead.mycollab.rest.server.signup.ExistingUserRegisterException;
import com.esofthead.mycollab.rest.server.signup.SubdomainExistedException;

public interface UserHubResource {
	@Post("form")
	public String doPost(Form form) throws SubdomainExistedException,
			ExistingEmailRegisterException, ExistingUserRegisterException;
}
