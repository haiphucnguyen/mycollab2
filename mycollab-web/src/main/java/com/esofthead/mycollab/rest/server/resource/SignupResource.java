package com.esofthead.mycollab.rest.server.resource;

import org.json.JSONException;
import org.restlet.data.Form;
import org.restlet.resource.Post;

public interface SignupResource {
	@Post("form")
	abstract public String doPost(Form form) throws JSONException;
}
