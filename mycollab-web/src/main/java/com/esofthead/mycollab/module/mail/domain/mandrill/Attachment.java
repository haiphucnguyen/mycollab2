package com.esofthead.mycollab.module.mail.domain.mandrill;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

public class Attachment {
	
	private String type;
	private String name;
	private String content;
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getContent() {
		return content;
	}
	
	public static final Attachment load(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			Attachment result = new Attachment();
			result.name = file.getName();
			
			if (isApplicationPdf(file.getName())) {
				result.type = "application/pdf";
			} else if (isImageFile(file.getName())) {
				String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
				result.type = "image/" + fileExtension.toLowerCase();
			} else if (isTextFile(file.getName())) {
				String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
				if (fileExtension.toLowerCase().equals("txt"))
					result.type = "text/plain";
				else result.type = "text/" + fileExtension.toLowerCase();
			} else {
				return null;
			}
			byte[] buffer = new byte[4096];
			int byteCount;
			try {
				InputStream inStream = new FileInputStream(file);
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				while ((byteCount = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, byteCount);
				}
				inStream.close();
				
				outStream.flush();
				outStream.close();
				
				result.content = new String(Base64.encodeBase64(outStream.toByteArray()));
				return result;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	
	private static final boolean isApplicationPdf(String fileName) {
		return fileName.endsWith(".pdf");
	}
	
	private static final boolean isImageFile(String fileName) {
		return fileName.endsWith(".gif")
				|| fileName.endsWith(".jpeg")
				|| fileName.endsWith(".pjpeg")
				|| fileName.endsWith(".png")
				|| fileName.endsWith(".svg")
				|| fileName.endsWith(".tiff")
				|| fileName.endsWith(".ico")
				|| fileName.endsWith(".bmp")
				|| fileName.endsWith(".jpg");
	}
	
	private static final boolean isTextFile(String fileName) {
		return fileName.endsWith(".cmd")
				|| fileName.endsWith(".css")
				|| fileName.endsWith(".js")
				|| fileName.endsWith(".csv")
				|| fileName.endsWith(".html")
				|| fileName.endsWith(".html")
				|| fileName.endsWith(".xml")
				|| fileName.endsWith(".json")
				|| fileName.endsWith(".vcard")
				|| fileName.endsWith(".txt");
	}
}
