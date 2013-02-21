package com.esofthead.mycollab.module.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class TemplateGenerator {
	private String subjectTemplate;
	private String contentTemplatePathFile;
	private VelocityContext velocityContext;

	public TemplateGenerator(String subjectTemplate,
			String contentTemplatePathFile) {
		velocityContext = new VelocityContext();
		this.subjectTemplate = subjectTemplate;
		this.contentTemplatePathFile = contentTemplatePathFile;
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
		return writer.toString();
	}
}
