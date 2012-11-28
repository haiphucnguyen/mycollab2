package com.esofthead.mycollab.validator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class URLValidator implements ConstraintValidator<URL, String> {

	@Override
	public void initialize(URL constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			return value
					.matches("[-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\.[a-z]{2,4}\\b(\\/[-a-zA-Z0-9@:%_\\+.~#?&//=]*)?");
		} else {
			return true;
		}
	}
}
