package com.esofthead.mycollab.usertracking;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class HttpDownloader {
	/**
	 * 5 seconds to accept the connection
	 */
	private static final int TIME_OUT = 5;

	public static final void downloadFile(String requestUrl, String fileName)
			throws Exception {
		FileOutputStream fos = new FileOutputStream(new File(fileName));
		downloadFile(requestUrl, fos);
	}
	
	public static final void downloadFile(String requestUrl,
			OutputStream outStream) throws Exception {
		URL url = new URL(requestUrl);
		URLConnection connection = url.openConnection();
		connection.setConnectTimeout(TIME_OUT * 1000);

		connection.connect();

		BufferedInputStream input = new BufferedInputStream(
				connection.getInputStream());

		int count;
		byte data[] = new byte[1024];
		while ((count = input.read(data)) != -1) {
			outStream.write(data, 0, count);
		}
		input.close();

		outStream.flush();
		outStream.close();
	}
}
