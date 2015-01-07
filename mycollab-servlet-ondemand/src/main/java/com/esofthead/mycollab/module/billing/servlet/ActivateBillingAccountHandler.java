package com.esofthead.mycollab.module.billing.servlet;

import com.esofthead.mycollab.servlet.GenericHttpServlet;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
@WebServlet(name = "activateBillingAccountServlet", urlPatterns = "/billing/activate")
public class ActivateBillingAccountHandler extends GenericHttpServlet {

	@Override
	protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello world");

	}

	public static void main(String[] args) throws
			IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(
				"https://api.fastspring.com/company/esofthead/subscription/haiphucnguyen@gmail.com");
		// add headers
		String credentials = "hainguyen@mycollab.com" + ":" + "4243DeREclEt";
		String encodedAuth = "Basic "
				+ new String(
						Base64.encodeBase64(credentials.getBytes("UTF-8")),
						"UTF-8");
		httpGet.addHeader("Authorization", encodedAuth);
		CloseableHttpResponse response;
		// execute method
		response = httpClient.execute(httpGet);
		// write response content to disk
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			response.getEntity().writeTo(output);
			System.out.println(new String(output.toByteArray()));
		} finally {
			response.close();
			output.close();
		}
	}
}
