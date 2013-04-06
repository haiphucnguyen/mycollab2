package com.esofthead.mycollab.rest.server.resource;

import org.json.JSONException;
import org.restlet.Server;
import org.restlet.data.Form;
import org.restlet.data.Protocol;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.user.service.UserService;

@Component("restUserResource")
public class SignupResource extends ServerResource {
	private static Logger log = LoggerFactory.getLogger(SignupResource.class);

	@Autowired
	UserService userService;

	@Post("form")
	public String doPost(Form form) throws JSONException {
		log.debug("Receive input: " + form + " "
				+ BeanUtility.printBeanObj(form));
		return "aaa";
	}

	public static void main(String[] args) throws Exception {
		// Create the HTTP server and listen on port 8182
		Server server = new Server(Protocol.HTTP, 8182, SignupResource.class);
		server.start();
	}
}
