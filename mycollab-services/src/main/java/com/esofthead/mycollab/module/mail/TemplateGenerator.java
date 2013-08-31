package com.esofthead.mycollab.module.mail;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.configuration.SharingOptions;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.template.velocity.TemplateContext;
import com.esofthead.template.velocity.TemplateEngine;

public class TemplateGenerator {
	private final String subjectTemplate;
	private final String contentTemplatePathFile;
	private final TemplateContext templateContext;

	public TemplateGenerator(String subjectTemplate,
			String contentTemplatePathFile) {

		templateContext = new TemplateContext();
		this.subjectTemplate = subjectTemplate;
		this.contentTemplatePathFile = contentTemplatePathFile;

		Map<String, String> defaultUrls = new HashMap<String, String>();

		SharingOptions sharingOptions = SiteConfiguration.getSharingOptions();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
		defaultUrls.put("facebook_url", sharingOptions.getFacebookUrl());
		defaultUrls.put("google_url", sharingOptions.getGoogleplusUrl());
		defaultUrls.put("linkedin_url", sharingOptions.getLinkedinUrl());
		defaultUrls.put("twitter_url", sharingOptions.getTwitterUrl());

		templateContext.put("defaultUrls", defaultUrls);
	}

	public void putVariable(String key, Object value) {
		templateContext.put(key, value);
	}

	public String generateSubjectContent() {
		StringWriter writer = new StringWriter();
		Reader reader = new StringReader(subjectTemplate);
		TemplateEngine.evaluate(templateContext, writer, "log task", reader);
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

		TemplateEngine.evaluate(templateContext, writer, "log task", reader);
		return writer.toString();
	}
}
