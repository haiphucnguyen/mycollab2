package com.esofthead.mycollab.module.project.domain.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ShortnameProjectCheckingValidator.class)
@Documented
public @interface ShortnameProjectChecking {

	String message() default "{com.esofthead.mycollab.validator.constraints.ShortnameProjectCheckingValidator}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
