package com.esofthead.mycollab.rest;

import org.json.JSONException;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.user.service.UserService;

@Component("restUserResource")
public class UserResource extends ServerResource {
	private static Logger log = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	UserService userService;
	
	public UserResource() {
		this.getConverterService().setEnabled(true);
	}
	
//	@Get("json")
//	public Representation signin() {
//		String format = this.getQueryValue("format1");
//		System.out.println("FORMAT: " + format);
//		GroupItem item = new GroupItem();
//		item.setGroupname("AAA");
//		item.setGroupid("1");
//		item.setValue(4);
//		return new JsonRepresentation(item);
//	}
	
	@Get("json")
	public GroupItem signin() {
		String format = this.getQueryValue("format1");
		System.out.println("FORMAT: " + format);
		GroupItem item = new GroupItem();
		item.setGroupname("AAA");
		item.setGroupid("1");
		item.setValue(4);
		return item;
	}

	@Post()
	public String signup(GroupItem item) throws JSONException {
		log.debug("Receive input: " + item + " "
				+ BeanUtility.printBeanObj(item));
		return "aaa";
	}

	public static void main(String[] args) throws Exception {
		// Create the HTTP server and listen on port 8182
		Server server = new Server(Protocol.HTTP, 8182, UserResource.class);
		server.start();
	}
}
