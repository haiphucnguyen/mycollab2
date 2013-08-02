package com.esofthead.mycollab.rest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.esofthead.mycollab.rest.server.resource.UserHubResource;

public class Test {

	public static void main(String[] args) throws ClientProtocolException,
			IOException, JSONException {

		ClientResource clientResource = new ClientResource(
				"http://localhost:8080/mycollab-web/api/signup");
		UserHubResource testResource = clientResource
				.wrap(UserHubResource.class);

		try {
			Form form = new Form();
			form.set("subdomain", "esofthead6");
			form.set("planId", "1");
			form.set("username", "hainguyen6");
			form.set("password", "123");
			form.set("email", "hainguyen@esofthead6.com");
			form.set("timezoneId", "10");
			form.set("firstname", "Nguyen");
			form.set("lastname", "Hai");

			try {
				String result = testResource.signup(form);
				if (result != null) {
					System.out.println(result);
				}
			} catch (ResourceException e) {
				System.out.println("EXCEPTION1: " + e.getCause() + "---"
						+ e.getMessage());
				System.out.println(clientResource.getResponse().getEntity()
						.getText());

			}

		} catch (Exception e) {
			System.out.println("EX: " + e.getClass());
			e.printStackTrace();
		}

	}
}
