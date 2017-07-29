package com.mycollab.validator.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = StringLimitLengthValidator.class)
@Documented
public @interface StringLimitLength {

    String message() default "{com.mycollab.validator.constraints.StringLimitLengthValidator}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
