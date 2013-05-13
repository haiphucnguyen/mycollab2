package com.esofthead.mycollab.rest.server.resource;

import org.json.JSONException;
import org.restlet.data.Form;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public abstract class SignupResource extends ServerResource {
	@Post("form")
	abstract public String doPost(Form form) throws JSONException;
}
