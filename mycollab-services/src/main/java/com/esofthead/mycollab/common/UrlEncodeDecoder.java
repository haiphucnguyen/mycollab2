/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;

/**
 * 
 * @author haiphucnguyen
 */
public class UrlEncodeDecoder {
	private static Logger log = LoggerFactory.getLogger(UrlEncodeDecoder.class);

	public static String encode(String str) {
		try {
			return URLEncoder.encode(
					new String(
							Base64.encodeBase64URLSafe(str.getBytes("UTF-8")),
							"UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			throw new MyCollabException(ex);
		}
	}

	public static String decode(String str) {
		try {
			String decodeStr = URLDecoder.decode(str, "UTF8");
			decodeStr = new String(Base64.decodeBase64(decodeStr
					.getBytes("UTF-8")), "UTF-8");
			return decodeStr;
		} catch (Exception e) {
			log.error("Error while decode string: " + str);
			return "";
		}
	}

	public static String encode(Number str) {
		return encode(str.toString());
	}
}
