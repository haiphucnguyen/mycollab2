package com.esofthead.mycollab.common;

import java.io.IOException;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class ApplicationProperties {
	private static final String RESOURCE_PROPERTIES = "resources.properties";
	private static final String DECRYPT_PASS = "esofthead321";
	
	private static Properties properties;

	static {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(DECRYPT_PASS);
		
		properties = new EncryptableProperties(encryptor);
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(RESOURCE_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Properties getAppProperties() {
		return properties;
	}
	
	public static String getSendErrorEmail() {
		return properties.getProperty("error.sendTo");
	}
}
