package com.mycollab.configuration;

import com.mycollab.core.MyCollabException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Utility class to make encrypt and decrypt text
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class EnDecryptHelper {
    private static StrongPasswordEncryptor strongEncryptor = new StrongPasswordEncryptor();
    private static BasicTextEncryptor basicTextEncryptor;

    static {
        basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(SiteConfiguration.getEnDecryptPassword());
    }

    /**
     * Encrypt password
     *
     * @param password
     * @return
     */
    public static String encryptSaltPassword(String password) {
        return strongEncryptor.encryptPassword(password);
    }

    public static String encryptText(String text) {
        return basicTextEncryptor.encrypt(text);
    }

    public static String encryptTextWithEncodeFriendly(String text) {
        try {
            return URLEncoder.encode(basicTextEncryptor.encrypt(text), "ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new MyCollabException(e);
        }
    }

    public static String decryptText(String text) {
        try {
            return basicTextEncryptor.decrypt(text);
        } catch (EncryptionOperationNotPossibleException e) {
            throw new MyCollabException("Can not decrypt the text--" + text + "---");
        }
    }

    public static String decryptTextWithEncodeFriendly(String text) {
        try {
            return basicTextEncryptor.decrypt(URLDecoder.decode(text, "ASCII"));
        } catch (Exception e) {
            throw new MyCollabException("Can not decrypt the text--" + text + "---", e);
        }
    }

    /**
     * Check password <code>inputPassword</code> match with
     * <code>expectedPassword</code> in case <code>inputPassword</code> encrypt
     * or not
     *
     * @param inputPassword
     * @param expectedPassword
     * @param isPasswordEncrypt flag to denote <code>inputPassword</code> is encrypted or not
     * @return
     */
    public static boolean checkPassword(String inputPassword, String expectedPassword, boolean isPasswordEncrypt) {
        if (!isPasswordEncrypt) {
            return strongEncryptor.checkPassword(inputPassword, expectedPassword);
        } else {
            return inputPassword.equals(expectedPassword);
        }
    }
}
