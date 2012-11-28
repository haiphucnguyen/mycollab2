package com.esofthead.mycollab.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements
		ConstraintValidator<PhoneNumber, String> {

	@Override
	public void initialize(PhoneNumber constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			return value
					.matches("[(]?\\d{3}[)]?\\s?-?\\s?\\d{3}\\s?-?\\s?\\d{4}");
		} else {
			return true;
		}
	}

}
