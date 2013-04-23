package com.esofthead.mycollab.module.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;
import org.apache.velocity.tools.generic.DateTool;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.SimpleTask;

public class TemplateGenerator {
	private final String subjectTemplate;
	private final String contentTemplatePathFile;
	private final VelocityContext velocityContext;

	private static ToolManager toolManager;
	private VelocityEngine voEngine;

	static {
		EasyFactoryConfiguration config = new EasyFactoryConfiguration();
		config.toolbox(Scope.APPLICATION).tool(DateTool.class);

		toolManager = new ToolManager();
		toolManager.configure(config);
	}

	public TemplateGenerator(String subjectTemplate,
			String contentTemplatePathFile) {
		voEngine = new VelocityEngine();
		voEngine.init();

		// Velocity.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM, this);

		velocityContext = new VelocityContext(toolManager.createContext());
		this.subjectTemplate = subjectTemplate;
		this.contentTemplatePathFile = contentTemplatePathFile;

		Map<String, String> defaultUrls = new HashMap<String, String>();

		defaultUrls.put("app_url", ApplicationProperties
				.getProperty(ApplicationProperties.APP_URL));
		defaultUrls.put("cdn_url", ApplicationProperties
				.getProperty(ApplicationProperties.CDN_URL));
		defaultUrls.put("facebook_url", ApplicationProperties
				.getProperty(ApplicationProperties.FACEBOOK_URL));
		defaultUrls.put("google_url", ApplicationProperties
				.getProperty(ApplicationProperties.GOOGLE_URL));
		defaultUrls.put("linkedin_url", ApplicationProperties
				.getProperty(ApplicationProperties.LINKEDIN_URL));
		defaultUrls.put("twitter_url", ApplicationProperties
				.getProperty(ApplicationProperties.TWITTER_URL));

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

		voEngine.evaluate(velocityContext, writer, "log task", reader);
		return writer.toString();
	}

	public static void main(String[] args) {
		TemplateGenerator a = new TemplateGenerator("AAA",
				"templates/email/project/test.mt");
		SimpleMessage msg = new SimpleMessage();
		msg.setTitle("Hải Nguyễn");
		a.putVariable("msg", msg);
		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put("taskUrl", "#");
		hyperLinks.put("projectUrl", "#");
		hyperLinks.put("assignUserUrl", "#");
		hyperLinks.put("taskListUrl", "#");
		a.putVariable("hyperLinks", hyperLinks);
		System.out.println(a.generateBodyContent());
	}
}
