/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common;

import com.esofthead.mycollab.core.MyCollabException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author haiphucnguyen
 */
public class UrlEncodeDecoder {

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

    public static String encode(Number str) {
        return encode(str.toString());
    }
    
    public static void main(String[] args) {
        String txt = textEncryptor.encrypt("2");
        System.out.println(txt);
        System.out.println(textEncryptor.decrypt(txt));
    }
}
