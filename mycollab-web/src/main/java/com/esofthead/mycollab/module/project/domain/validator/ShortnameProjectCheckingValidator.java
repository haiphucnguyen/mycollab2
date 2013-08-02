package com.esofthead.mycollab.module.project.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ShortnameProjectCheckingValidator implements
		ConstraintValidator<ShortnameProjectChecking, String> {

	@Override
	public void initialize(ShortnameProjectChecking constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
            return (value.length() == 3);
        } else {
            return true;
        }
	}

}
