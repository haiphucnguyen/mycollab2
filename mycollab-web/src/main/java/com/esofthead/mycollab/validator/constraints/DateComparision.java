/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.validator.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author haiphucnguyen
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DateComparisionValidator.class)
@Documented
public @interface DateComparision {
    String message() default "{com.esofthead.mycollab.validator.constraints.DateComparision}";
    
    String firstDateField();
    
    String lastDateField();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
