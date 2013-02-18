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
			return passwordEncryptor.checkPassword(plainPassword,
					encryptPassword);
		}
	}

	public static void main(String[] args) {
		System.out
				.println(PasswordEncryptHelper
						.checkPassword(
								"123456",
								"8Ik7q/G+X7EmJbCOC6ZmMEuZlfUePZqaRRvCAy2thRrlxwx5G9v2JYMt423xQxWo",
								false));
	}
}
