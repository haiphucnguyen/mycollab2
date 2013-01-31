package com.esofthead.mycollab.module.mail.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

public class CampaignManager {

	public static final SendResponse sendCampaignMessage(String json) {
		final String requestUrl = "https://mandrillapp.com/api/1.0/messages/send.json";
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(requestUrl);

		Gson gson = new Gson();
		String jsonContent = null;

		try {
			StringEntity params = new StringEntity(json);
			postRequest.addHeader("content-type",
					"application/x-www-form-urlencoded");
			postRequest.setEntity(params);
			HttpResponse response = client.execute(postRequest);

			byte[] responseContent = readResponse(response.getEntity()
					.getContent());
			jsonContent = new String(responseContent);
			return gson.fromJson(new StringReader(jsonContent),
					SendResponse.class);
		} catch (Exception e) {
			if (null != jsonContent)
				ErrorCollector.sendErrorMail(jsonContent, e.getMessage());
			else
				ErrorCollector.sendErrorMail("", e.getMessage());
			return null;
		}
	}
	
	public static final SendResponse sendCampaignMessage(SendRequest request) {
		Gson gson = new Gson();
		String json = gson.toJson(request);
		
		return sendCampaignMessage(json);
	}

	public static final SendResponse sendCampaignMessage(String html,
			String subject, String senderMail, String senderName,
			List<Recipient> to, String bbcAddress, String attachFile) {
		Message message = new Message();
		SendRequest request = new SendRequest();
		request.setMessage(message);
		
		message.setHtml(html);
		message.setSubject(subject);
		message.setFrom_email(senderMail);
		if (null != senderName && senderName.length() > 0) {
			message.setFrom_name(senderName);
		}
		
		for (Recipient recipient : to) {
			message.addRecipient(recipient);
		}
		
		if (null != bbcAddress && bbcAddress.length() > 0) {
			message.setBcc_address(bbcAddress);
		}
		
		if (null != attachFile && !message.addAttachMent(attachFile)) {
			return null;
		}
		
		return sendCampaignMessage(request);
	}
	

	public static final StatisticsResponse statistic(String sender) {
		final String requestUrl = "https://mandrillapp.com/api/1.0/senders/info.json";
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(requestUrl);

		StatisticsQuery query = new StatisticsQuery();
		query.setAddress(sender);

		Gson gson = new Gson();
		String json = gson.toJson(query);

		String jsonContent = null;

		try {
			StringEntity params = new StringEntity(json);
			postRequest.addHeader("content-type",
					"application/x-www-form-urlencoded");
			postRequest.setEntity(params);
			HttpResponse response = client.execute(postRequest);

			byte[] responseContent = readResponse(response.getEntity()
					.getContent());

			jsonContent = new String(responseContent);
			return gson.fromJson(new StringReader(jsonContent),
					StatisticsResponseImpl.class);
		} catch (Exception e) {
			if (null != jsonContent)
				ErrorCollector.sendErrorMail(jsonContent, e.getMessage());
			else
				ErrorCollector.sendErrorMail("", e.getMessage());
			return null;
		}
	}

	private static final byte[] readResponse(InputStream in) {
		byte buffer[] = new byte[1024];
		int readCount;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			while ((readCount = in.read(buffer)) != -1) {
				outStream.write(buffer, 0, readCount);
			}
			in.close();

			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			return null;
		}
		return outStream.toByteArray();
	}
}
