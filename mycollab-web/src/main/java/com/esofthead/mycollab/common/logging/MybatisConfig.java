package com.esofthead.mycollab.common.logging;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MybatisConfig {
	private static final String CONFIG_FILE = "mybatis.properties.xml";

	public static final String loadConfig() {
		CommonConfig config = CommonConfig.loadConfig();
		if (null != config) {
			try {
				InputStream inputStream = Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream(CONFIG_FILE);
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int byteCount;
				while ((byteCount = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, byteCount);
				}
				inputStream.close();
				outStream.flush();
				outStream.close();

				String configContent = new String(outStream.toByteArray());
				return String.format(configContent, config.getClassName(),
						config.getUrl(), config.getUserName(),
						config.getPassword());
			} catch (Exception e) {

			}
		}
		return null;
	}
}
