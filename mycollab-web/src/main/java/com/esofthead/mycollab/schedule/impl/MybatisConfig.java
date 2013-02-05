package com.esofthead.mycollab.schedule.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class MybatisConfig {
	private static final String MYBATIS_PROPERTIES = "mybatis.properties.xml";
	private static final String RESOURCE_PROPERTIES = "resources.properties";
	
	private static final String CLASS_NAME = "db.driverClassName";
	private static final String URL = "db.url";
	private static final String USER_NAME = "db.username";
	private static final String PASSWORD = "db.password";
	
	private static final String DECRYPT_PASS = "esofthead321";

	public static final String loadConfig() {

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(DECRYPT_PASS);
		Properties resourceProperties = new EncryptableProperties(encryptor);
		try {
			resourceProperties.load(Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(RESOURCE_PROPERTIES));
			String className = resourceProperties.getProperty(CLASS_NAME);
			String url = resourceProperties.getProperty(URL);
			String userName = resourceProperties.getProperty(USER_NAME);
			String password = resourceProperties.getProperty(PASSWORD);

			String mybatisContent = getMybatisTemplateContent();
			if (null != mybatisContent) {
				return String.format(mybatisContent, className, url, userName,
						password);
			}
		} catch (Exception e) {
		}
		return null;
	}

	private static final String getMybatisTemplateContent() {
		try {
			InputStream inputStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(MYBATIS_PROPERTIES);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int byteCount;
			while ((byteCount = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, byteCount);
			}
			inputStream.close();
			outStream.flush();
			outStream.close();

			return new String(outStream.toByteArray());
		} catch (Exception e) {

		}

		return null;
	}
}
