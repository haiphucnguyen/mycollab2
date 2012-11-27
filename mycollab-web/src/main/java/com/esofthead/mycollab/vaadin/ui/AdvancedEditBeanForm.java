package com.esofthead.mycollab.vaadin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.web.AppContext;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings("serial")
public class AdvancedEditBeanForm<T> extends GenericForm implements
		HasEditFormHandlers<T> {
	private Validator validation;

	private List<EditFormHandler<T>> editFormHandlers;

	public AdvancedEditBeanForm() {
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

	public void addFormHandler(EditFormHandler<T> editFormHandler) {
		if (editFormHandlers == null) {
			editFormHandlers = new ArrayList<EditFormHandler<T>>();
		}

		editFormHandlers.add(editFormHandler);
	}

	protected void fireSaveForm(T bean) {
		if (editFormHandlers != null) {
			for (EditFormHandler<T> editFormHandler : editFormHandlers) {
				editFormHandler.onSave(bean);
			}
		}
	}

	protected void fireSaveAndNewForm(T bean) {
		if (editFormHandlers != null) {
			for (EditFormHandler<T> editFormHandler : editFormHandlers) {
				editFormHandler.onSaveAndNew(bean);
			}
		}
	}

	protected void fireCancelForm() {
		if (editFormHandlers != null) {
			for (EditFormHandler<T> editFormHandler : editFormHandlers) {
				editFormHandler.onCancel();
			}
		}
	}
}
