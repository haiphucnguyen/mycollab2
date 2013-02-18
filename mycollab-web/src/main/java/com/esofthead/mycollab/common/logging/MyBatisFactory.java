package com.esofthead.mycollab.common.logging;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.esofthead.mycollab.common.ApplicationProperties;

public class MyBatisFactory {
	private static final String MYBATIS_PROPERTIES = "mybatis.properties.xml";

	private static final String CLASS_NAME = "db.driverClassName";
	private static final String URL = "db.url";
	private static final String USER_NAME = "db.username";
	private static final String PASSWORD = "db.password";

	private static final MyBatisFactory instance = new MyBatisFactory();

	private SqlSessionFactory sessionFactory;

	public MyBatisFactory() {
		String xmlConfig = loadConfig();
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		sessionFactory = builder.build(new StringReader(xmlConfig));
	}

	private static final String loadConfig() {
		try {
			Properties resourceProperties = ApplicationProperties
					.getAppProperties();
			String className = resourceProperties.getProperty(CLASS_NAME);
			String url = resourceProperties.getProperty(URL);
			String userName = resourceProperties.getProperty(USER_NAME);
			String password = resourceProperties.getProperty(PASSWORD);

			String mybatisContent = getMybatisTemplateContent();
			if (mybatisContent != null) {
				return String.format(mybatisContent, className, url, userName,
						password);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public static SqlSessionFactory build() {
		return instance.sessionFactory;
	}
}
