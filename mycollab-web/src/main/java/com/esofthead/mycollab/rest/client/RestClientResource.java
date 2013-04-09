package com.esofthead.mycollab.rest.client;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;

public class RestClientResource extends ClientResource {
	public RestClientResource(String url) {
		super(url);
	}

	@Override
	public void doError(Status errorStatus) {
		System.out.println("Handle error: " + errorStatus.getCode() + "--"
				+ errorStatus.getDescription());
	}
}
