package com.mycollab.configuration;

import com.mycollab.core.MyCollabException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

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
        basicTextEncryptor.setPassword("esofthead321");
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

    public static String decryptText(String text) {
        try {
            return basicTextEncryptor.decrypt(text);
        } catch (EncryptionOperationNotPossibleException e) {
            throw new MyCollabException("Can not decrypt the text--" + text + "---");
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
