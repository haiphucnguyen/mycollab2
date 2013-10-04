package com.esofthead.mycollab.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class RemoteServiceProxy {
	public static <T> T build(String uri, Class<T> remoteService) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
		return rtarget.proxy(remoteService);
	}
}
