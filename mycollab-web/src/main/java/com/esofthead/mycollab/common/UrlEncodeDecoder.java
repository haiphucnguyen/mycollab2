/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common;

import com.esofthead.mycollab.core.MyCollabException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author haiphucnguyen
 */
public class UrlEncodeDecoder {
	private static Logger log = LoggerFactory.getLogger(UrlEncodeDecoder.class);

	private static BasicTextEncryptor textEncryptor;

	static {
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("esofthead321");
	}

	public static String encode(String str) {
		try {
			return URLEncoder.encode(textEncryptor.encrypt(str), "UTF8");
		} catch (UnsupportedEncodingException ex) {
			throw new MyCollabException(ex);
		}
	}

	public static String decode(String str) {
		try {
			String decodeStr = URLDecoder.decode(str, "UTF8");
			decodeStr = textEncryptor.decrypt(decodeStr);
			return decodeStr;
		} catch (Exception e) {
			log.error("Error while decode string: " + str);
			return "";
		}
	}

	public static String encode(Number str) {
		return encode(str.toString());
	}

	public static void main(String[] args) {
		String txt = textEncryptor.encrypt("2");
		System.out.println(txt);
		System.out.println(textEncryptor.decrypt(txt));
	}
}
