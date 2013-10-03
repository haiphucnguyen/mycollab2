/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.validator.constraints;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 * @author haiphucnguyen
 */
public class DateComparisionValidator implements
		ConstraintValidator<DateComparision, Object> {

	private String firstDateField;
	private String lastDateField;

	@Override
	public void initialize(DateComparision constraintAnnotation) {
		this.firstDateField = constraintAnnotation.firstDateField();
		this.lastDateField = constraintAnnotation.lastDateField();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			Date firstDate = (Date) PropertyUtils.getProperty(value,
					firstDateField);
			Date lastDate = (Date) PropertyUtils.getProperty(value,
					lastDateField);

			if (firstDate == null || lastDate == null) {
				return true;
			} else {

				return (firstDate.compareTo(lastDate) == 1) ? false : true;
			}
		} catch (Exception ex) {
			return true;
		}
	}
}
