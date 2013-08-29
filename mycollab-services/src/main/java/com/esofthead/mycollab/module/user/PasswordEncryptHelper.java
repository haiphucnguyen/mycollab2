package com.esofthead.mycollab.module.user;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

public class PasswordEncryptHelper {
	private static StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
	private static BasicTextEncryptor basicTextEncryptor;

	static {
		basicTextEncryptor = new BasicTextEncryptor();
		basicTextEncryptor.setPassword("esofthead321");
	}

	public static String encryptSaltPassword(String password) {
		return passwordEncryptor.encryptPassword(password);
	}

	public static String encyptText(String text) {
		return basicTextEncryptor.encrypt(text);
	}

	public static String decryptText(String text) {
		return basicTextEncryptor.decrypt(text);
	}

	public static boolean checkPassword(String plainPassword,
			String encryptPassword, boolean isPasswordEncrypt) {
		if (!isPasswordEncrypt) {
			return passwordEncryptor.checkPassword(plainPassword,
					encryptPassword);
		} else {
			return plainPassword.equals(encryptPassword);
		}
	}

	public static void main(String[] args) {
		System.out
				.println(PasswordEncryptHelper
						.checkPassword(
								"123456",
								"6Cb2D0XBfkuQBaNuwrvdpeEfb9+F1wtcQoIB4njIhaC70wz7COQ6zbXm8loVayML",
								true));
		System.out.println(decryptText("OksKWiyWRjGOtkVg4feU9Q=="));
	}
}
