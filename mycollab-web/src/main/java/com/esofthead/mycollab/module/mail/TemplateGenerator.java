package com.esofthead.mycollab.module.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.esofthead.mycollab.common.ApplicationProperties;

public class TemplateGenerator {
	private final String subjectTemplate;
	private final String contentTemplatePathFile;
	private final VelocityContext velocityContext;

	public TemplateGenerator(String subjectTemplate,
			String contentTemplatePathFile) {
		velocityContext = new VelocityContext();
		this.subjectTemplate = subjectTemplate;
		this.contentTemplatePathFile = contentTemplatePathFile;

		Map<String, String> defaultUrls = new HashMap<String, String>();

		defaultUrls
				.put("app_url", ApplicationProperties.getProperty("APP_URL"));
		defaultUrls
				.put("cdn_url", ApplicationProperties.getProperty("CDN_URL"));
		defaultUrls.put("facebook_url",
				ApplicationProperties.getProperty("FACEBOOK_URL"));
		defaultUrls.put("google_url",
				ApplicationProperties.getProperty("GOOGLE_URL"));
		defaultUrls.put("linkedin_url",
				ApplicationProperties.getProperty("LINKEDIN_URL"));
		defaultUrls.put("twitter_url",
				ApplicationProperties.getProperty("TWITTER_URL"));

		velocityContext.put("defaultUrls", defaultUrls);
	}

	public void putVariable(String key, Object value) {
		velocityContext.put(key, value);
	}

	public String generateSubjectContent() {
		Velocity.init();
		StringWriter writer = new StringWriter();
		Reader reader = new StringReader(subjectTemplate);
		Velocity.evaluate(velocityContext, writer, "log task", reader);
		return writer.toString();
	}

	public String generateBodyContent() {
		Velocity.init();
		StringWriter writer = new StringWriter();
		Reader reader = new BufferedReader(new InputStreamReader(
				TemplateGenerator.class.getClassLoader().getResourceAsStream(
						contentTemplatePathFile)));
		Velocity.evaluate(velocityContext, writer, "log task", reader);
		String result = writer.toString();
		System.out.println("Result: " + result);
		return result;
	}
}
