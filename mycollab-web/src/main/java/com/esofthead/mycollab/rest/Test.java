package com.esofthead.mycollab.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import com.esofthead.mycollab.module.crm.domain.Customer;
import com.esofthead.mycollab.rest.server.resource.UserHubResource;

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
		// DefaultHttpClient httpClient = new DefaultHttpClient();
		// HttpPost postRequest = new HttpPost(
		// "http://localhost:8080/mycollab-web/api/signup");
		//
		// List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		// nvps.add(new BasicNameValuePair("username", "username1"));
		// nvps.add(new BasicNameValuePair("password", "password1"));
		// nvps.add(new BasicNameValuePair("planId", "1"));
		// nvps.add(new BasicNameValuePair("email", "hainguyen@esofthead.com"));
		//
		// postRequest.setEntity(new UrlEncodedFormEntity(nvps));
		// HttpResponse response = httpClient.execute(postRequest);
		// System.out.println(response.getEntity().getContent());

		ClientResource clientResource = new ClientResource(
				"http://localhost:8080/mycollab-web/api/signup");
		UserHubResource testResource = clientResource
				.wrap(UserHubResource.class);

		try {
			Form form = new Form();
			form.set("subdomain", "esofthead");
			form.set("planId", "1");
			form.set("username", "hainguyen1");
			form.set("password", "123");
			form.set("email", "hainguyen@esofthead1.com");
			form.set("timezoneId", "10");
			form.set("firstname", "Nguyen");
			form.set("lastname", "Hai");

			Representation result = clientResource.post(form,
					MediaType.MULTIPART_FORM_DATA);
			if (result != null) {
				System.out.println(result.getText());
			}
		} catch (Exception e) {
			System.out.println(e.getClass());
			e.printStackTrace();
		}

	}
}
