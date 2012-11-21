package com.esofthead.mycollab.vaadin.ui;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.esofthead.mycollab.vaadin.events.DefaultFormView;
import com.esofthead.mycollab.web.AppContext;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings("serial")
public class AdvancedEditBeanForm<T> extends DefaultFormView {
	private Validator validation;

	public AdvancedEditBeanForm() {
		super();
		validation = AppContext.getSpringBean(LocalValidatorFactoryBean.class);
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
