/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.configuration;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;


public class PasswordEncryptHelper {
	private static StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
	private static BasicTextEncryptor basicTextEncryptor;

	static {
		basicTextEncryptor = new BasicTextEncryptor();
		basicTextEncryptor
				.setPassword(SiteConfiguration.getEnDecryptPassword());
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
