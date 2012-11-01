package com.esofthead.mycollab.vaadin.ui;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.vaadin.mvp.eventbus.EventBus;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Form;

@SuppressWarnings("serial")
public class AdvancedBeanForm<T> extends Form {
	@Autowired
	protected Validator validation;

	@Autowired
	protected EventBus eventBus;
	
	protected boolean isEditMode;

	public AdvancedBeanForm() {
		this(true);
	}
	
	public AdvancedBeanForm(boolean isEditMode) {
		super();
		this.isEditMode = isEditMode;
	}

	protected boolean validateForm(Object data) {
		Set<ConstraintViolation<Object>> violations = validation.validate(data);
		if (violations.size() > 0) {
			for (@SuppressWarnings("rawtypes") ConstraintViolation violation : violations) {
				this.setComponentError(new UserError(violation.getMessage()));
			}
			return false;
		}

		return true;
	}
}
