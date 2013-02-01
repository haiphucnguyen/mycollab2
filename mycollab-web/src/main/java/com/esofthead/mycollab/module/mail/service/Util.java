package com.esofthead.mycollab.module.mail.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Util {
	public static final String getTemplateContent(String templatePath)
			throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		int count;
		byte data[] = new byte[4096];

		InputStream inputStream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(templatePath);
		while ((count = inputStream.read(data)) != -1) {
			outStream.write(data, 0, count);
		}
		inputStream.close();

		outStream.flush();
		outStream.close();

		return new String(outStream.toByteArray());
	}
}
