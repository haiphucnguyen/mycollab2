package com.esofthead.mycollab.pages;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaResponse;

public class ReCaptchaUtil {

	private static final ReCaptcha reCaptcha;

	static {
		reCaptcha = ReCaptchaFactory.newReCaptcha(
				"6Lfj3eoSAAAAAO732dbG8MYpvdbQelEyRCpX-F_J",
				"6Lfj3eoSAAAAACQppgZu_idhxCU7g3AOwLKE5jJC", false);
	}

	public static String createRecaptchaHtml() {
		return reCaptcha.createRecaptchaHtml(null, null);
	}

	public static ReCaptchaResponse checkAnswer(String remoteAddr,
			String challenge, String response) {
		return reCaptcha.checkAnswer(remoteAddr, challenge, response);
	}
}
