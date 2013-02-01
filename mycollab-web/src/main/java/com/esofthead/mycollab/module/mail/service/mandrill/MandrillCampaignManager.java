package com.esofthead.mycollab.module.mail.service.mandrill;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.esofthead.mycollab.module.mail.domain.mandrill.Message;
import com.esofthead.mycollab.module.mail.domain.mandrill.Recipient;
import com.esofthead.mycollab.module.mail.domain.mandrill.SendResponse;
import com.esofthead.mycollab.module.mail.domain.mandrill.StatisticsResponse;
import com.esofthead.mycollab.module.mail.service.Util;
import com.google.gson.Gson;

public class MandrillCampaignManager {
	
	private final SendResponse sendCampaignMessage(String json)
			throws Exception {
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
			throw e;
		}
	}

	private final SendResponse sendCampaignMessage(SendRequest request)
			throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(request);

		return sendCampaignMessage(json);
	}

	public final SendResponse sendCampaignMessage(Message message)
			throws Exception {
		SendRequest request = new SendRequest();
		request.setMessage(message);
		return sendCampaignMessage(request);
	}

	public final SendResponse sendCampaignMessage(String html, String subject,
			String senderMail, String senderName, List<Recipient> to,
			String bbcAddress, String attachFile) throws Exception {
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

	public final SendResponse sendTemplateCampaignMessage(String template,
			HashMap<String, String> map, String subject, String senderMail,
			String senderName, List<Recipient> to, String bbcAddress,
			String attachFile) throws Exception {
		String htmlContent = Util.getTemplateContent(template);

		for (Entry<String, String> entry : map.entrySet()) {
			htmlContent = htmlContent.replace(entry.getKey(),
					entry.getValue());
		}

		return sendCampaignMessage(htmlContent, subject, senderMail,
				senderName, to, bbcAddress, attachFile);
	}

	public final StatisticsResponse statistic(String sender) {
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

	private static final byte[] readResponse(InputStream in) throws Exception {
		byte buffer[] = new byte[1024];
		int readCount;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		while ((readCount = in.read(buffer)) != -1) {
			outStream.write(buffer, 0, readCount);
		}
		in.close();

		outStream.flush();
		outStream.close();
		
		return outStream.toByteArray();
	}
}
