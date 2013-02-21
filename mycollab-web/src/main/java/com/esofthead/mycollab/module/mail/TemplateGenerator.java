package com.esofthead.mycollab.module.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class TemplateGenerator {
	private String templatePath;
	private VelocityContext velocityContext;

	public TemplateGenerator(String templatePath) {
		velocityContext = new VelocityContext();
		this.templatePath = templatePath;
	}

	public void putVariable(String key, Object value) {
		velocityContext.put(key, value);
	}

	public String generateContent() {
		Velocity.init();
		StringWriter writer = new StringWriter();
		Reader reader = new BufferedReader(new InputStreamReader(
				TemplateGenerator.class.getClassLoader().getResourceAsStream(
						templatePath)));
		Velocity.evaluate(velocityContext, writer, "log task", reader);
		return writer.toString();
	}
}
