package com.esofthead.mycollab.module.user;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordEncryptHelper {
	private static StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

	public static String encryptPassword(String password) {
		return passwordEncryptor.encryptPassword(password);
	}

	public static boolean checkPassword(String plainPassword,
			String encryptPassword, boolean isPasswordEncrypt) {
		if (isPasswordEncrypt) {
			return plainPassword.equals(encryptPassword);
		} else {
			return passwordEncryptor.checkPassword(plainPassword, encryptPassword);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(PasswordEncryptHelper.encryptPassword("123456"));
	}
}
