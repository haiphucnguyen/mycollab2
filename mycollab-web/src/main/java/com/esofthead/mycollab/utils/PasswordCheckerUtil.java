package com.esofthead.mycollab.utils;

public class PasswordCheckerUtil {

	public static final String[] partialRegexChecks = { ".*[a-z]+.*", // lower
			".*[A-Z]+.*", // upper
			".*[\\d]+.*", // digits
			".*[@#$%]+.*" // symbols
	};

	public static boolean checkPasswordStrength(String password) {
		int strengthPercentage = 0;

		if (password.matches(partialRegexChecks[0])) {
			strengthPercentage += 25;
		}
		if (password.matches(partialRegexChecks[1])) {
			strengthPercentage += 25;
		}
		if (password.matches(partialRegexChecks[2])) {
			strengthPercentage += 25;
		}
		if (password.matches(partialRegexChecks[3])) {
			strengthPercentage += 25;
		}

		if (strengthPercentage >= 75)
			return true;
		else
			return false;
	}
}