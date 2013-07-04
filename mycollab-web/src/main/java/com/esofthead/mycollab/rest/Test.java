package com.esofthead.mycollab.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;

import com.esofthead.mycollab.module.crm.domain.Customer;
import com.esofthead.mycollab.rest.server.resource.SignupResource;

public class Test {
	// public static void main(String[] args) throws Exception {
	// ClientResource mailRoot = new RestClientResource(
	// "http://localhost:8080/mycollab-web/api/signup");
	//
	//
	// try {
	// Representation value = mailRoot.get(MediaType.APPLICATION_JSON);
	// Status status = mailRoot.getStatus();
	// System.out.println(status.getCode() + " ---"
	// + status.getDescription());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public static void main(String[] args) throws ClientProtocolException,
			IOException, JSONException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(
				"http://localhost:8182/mycollab-web/api/signup");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "username1"));
		nvps.add(new BasicNameValuePair("password", "password1"));
		nvps.add(new BasicNameValuePair("planId", "1"));
		nvps.add(new BasicNameValuePair("email", "hainguyen@esofthead.com"));

		postRequest.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpClient.execute(postRequest);
		System.out.println(response.getEntity().getContent());

		ClientResource clientResource = new ClientResource(
				"http://localhost:8182/mycollab-web/api/signup");
		SignupResource testResource = clientResource.wrap(SignupResource.class);

		// Retrieve the JSON value
		Form form = new Form();
		form.set("subdomain", "esofthead");
		form.set("username", "hainguyen");
		form.set("password", "abc");
		form.set("email", "hainguyen@esofthead.com");
		form.set("timezone", "UTC");
		form.set("planId", "1");
		String result = testResource.doPost(form);

		if (result != null) {
			System.out.println(result);
		}
	}
}
