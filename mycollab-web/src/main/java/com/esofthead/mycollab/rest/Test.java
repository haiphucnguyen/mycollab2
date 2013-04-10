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
			IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(
				"http://app.mycollab.com/api/signup");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "username1"));
		nvps.add(new BasicNameValuePair("password", "password1"));

		postRequest.setEntity(new UrlEncodedFormEntity(nvps));
		HttpResponse response = httpClient.execute(postRequest);
		System.out.println(response.getStatusLine());
	}
}
