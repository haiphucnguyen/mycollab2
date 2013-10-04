package com.esofthead.mycollab.rest.server.resource.impl;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.spring.SpringContextLoaderListener;

import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.rest.client.RemoteServiceProxy;
import com.esofthead.mycollab.rest.server.resource.UserResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {
	public static void main(String[] args) {
		UserResource userResource = RemoteServiceProxy.build(
				"http://localhost:8080/api", UserResource.class);
		Response response = userResource
				.getSubdomainsOfUser("hainguyen@esofthead.com1");
//		System.out.println(response.readEntity(String.class) + "--"
//				+ response.getMediaType() + "--" + response.getLocation());

		Gson gson = new GsonBuilder().create();
		String[] fromJson = gson.fromJson(response.readEntity(String.class),
				String[].class);
		System.out.println(BeanUtility.printBeanObj(fromJson));
	}
}
