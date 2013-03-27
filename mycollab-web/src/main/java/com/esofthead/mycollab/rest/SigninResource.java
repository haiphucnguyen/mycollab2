package com.esofthead.mycollab.rest;

import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class SigninResource extends ServerResource {
	@Get
	public String represent() {
		return "hello, world";
	}
	
	public static void main(String[] args) throws Exception {  
        // Create the HTTP server and listen on port 8182  
        new Server(Protocol.HTTP, 8182, SigninResource.class).start();  
    }  
}
