package com.esofthead.mycollab.module.mail;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.esofthead.mycollab.configuration.SharingOptions;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.template.velocity.EngineFactory;

public class TemplateGenerator {
	private final String subjectTemplate;
	private final String contentTemplatePathFile;
	private final VelocityContext velocityContext;

	public TemplateGenerator(String subjectTemplate,
			String contentTemplatePathFile) {

		velocityContext = new VelocityContext(EngineFactory.createContext());
		this.subjectTemplate = subjectTemplate;
		this.contentTemplatePathFile = contentTemplatePathFile;

		Map<String, String> defaultUrls = new HashMap<String, String>();

		SharingOptions sharingOptions = SiteConfiguration.getSharingOptions();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
		defaultUrls.put("facebook_url", sharingOptions.getFacebookUrl());
		defaultUrls.put("google_url", sharingOptions.getGoogleplusUrl());
		defaultUrls.put("linkedin_url", sharingOptions.getLinkedinUrl());
		defaultUrls.put("twitter_url", sharingOptions.getTwitterUrl());

		velocityContext.put("defaultUrls", defaultUrls);
	}

	public void putVariable(String key, Object value) {
		velocityContext.put(key, value);
	}

	public String generateSubjectContent() {
		StringWriter writer = new StringWriter();
		Reader reader = new StringReader(subjectTemplate);
		Velocity.evaluate(velocityContext, writer, "log task", reader);
		return writer.toString();
	}

	public String generateBodyContent() {
		StringWriter writer = new StringWriter();
		Reader reader;
		try {
			reader = new InputStreamReader(TemplateGenerator.class
					.getClassLoader().getResourceAsStream(
							contentTemplatePathFile), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(TemplateGenerator.class
					.getClassLoader().getResourceAsStream(
							contentTemplatePathFile));
		}

		EngineFactory.getTemplateEngine().evaluate(velocityContext, writer,
				"log task", reader);
		return writer.toString();
	}
}
