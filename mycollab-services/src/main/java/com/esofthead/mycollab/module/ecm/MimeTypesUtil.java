package com.esofthead.mycollab.module.ecm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.mime.MediaType;

public class MimeTypesUtil {
	public static String BINARY_MIME_TYPE = "application/octet-stream";

	public static String BINARY_TYPE = "application";

	public static String TEXT_TYPE = "text";

	public static String IMAGE_TYPE = "image";

	public static String AUDIO_TYPE = "audio";

	public static String VIDEO_TYPE = "video";

	private static Tika tika = new Tika();

	public static String detectMimeType(InputStream inStream) {
		try {
			return tika.detect(inStream);
		} catch (IOException e) {
			return BINARY_MIME_TYPE;
		}
	}

	public static String detectMimeType(String contentName) {
		return tika.detect(contentName);
	}

	public static String detectMyCollabContentType(String mimeType) {
		MediaType type = MediaType.parse(mimeType);
		return type.getType();
	}
}
