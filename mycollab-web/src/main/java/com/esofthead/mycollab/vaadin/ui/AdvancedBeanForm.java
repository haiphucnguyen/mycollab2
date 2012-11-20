package com.esofthead.mycollab.vaadin.ui;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.Form;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings("serial")
public class AdvancedBeanForm<T> extends Form {
	@Autowired
	protected Validator validation;

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
			StringBuffer errorMsg = new StringBuffer();

			for (@SuppressWarnings("rawtypes")
			ConstraintViolation violation : violations) {
				errorMsg.append(violation.getMessage()).append("<br/>");

			}

			MessageBox mb = new MessageBox(getWindow(), "Error!",
					MessageBox.Icon.ERROR, errorMsg.toString(),
					new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
			mb.show();

			return false;
		}

		return true;
	}
}
