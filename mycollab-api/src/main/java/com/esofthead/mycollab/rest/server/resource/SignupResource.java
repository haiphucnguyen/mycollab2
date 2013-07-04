package com.esofthead.mycollab.rest.server.resource;

import org.restlet.data.Form;
import org.restlet.resource.Post;

import com.esofthead.mycollab.core.MyCollabException;

public interface SignupResource {
	@Post("form")
	abstract public String doPost(Form form) throws MyCollabException;
}
