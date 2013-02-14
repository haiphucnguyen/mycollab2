package com.esofthead.mycollab.module.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityTemplate {
	public static String format(String templatePath, Object bean) {
		VelocityContext context = new VelocityContext();
		context.put("bean", bean);
		return format(context, templatePath);
	}

	public static String format(VelocityContext context, String templatePath) {
		Velocity.init();
		StringWriter writer = new StringWriter();
		Reader reader = new BufferedReader(new InputStreamReader(
				VelocityTemplate.class.getClassLoader().getResourceAsStream(
						templatePath)));
		Velocity.evaluate(context, writer, "log task", reader);
		return writer.toString();
	}

	public static void main(String[] args) {
		System.out.println(format("templates/email/errorReport.mt", null));
	}
}
