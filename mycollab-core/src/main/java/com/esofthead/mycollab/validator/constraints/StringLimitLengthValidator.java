package com.esofthead.mycollab.validator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringLimitLengthValidator implements
		ConstraintValidator<StringLimitLength, String> {

	@Override
	public void initialize(StringLimitLength constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
            return (value.length() <= 3);
        } else {
            return false;
        }
	}

}
