package com.esofthead.mycollab.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptText {

    private EncryptText() {
    }

    public static String encryptText(String input) {
        try {
            byte[] bytesOfMessage = input.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            BigInteger bigInt = new BigInteger(1, thedigest);
            return bigInt.toString(16);
        } catch (Exception ex) {
            return input;
        }
    }
}
