/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.ui.MessageBox.ButtonType;
import com.esofthead.mycollab.validator.constraints.DateComparision;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class AdvancedEditBeanForm<T> extends GenericForm implements
		HasEditFormHandlers<T> {

	private final Validator validation;
	private List<EditFormHandler<T>> editFormHandlers;

	public AdvancedEditBeanForm() {
		this.setImmediate(true);
		this.setWriteThrough(true);
		validation = ApplicationContextUtil.getSpringBean(LocalValidatorFactoryBean.class);
	}

	public boolean validateForm(Object data) {
		for (Object propertyId : this.getItemPropertyIds()) {
			this.getField(propertyId).removeStyleName("errorField");
		}
		Set<ConstraintViolation<Object>> violations = validation.validate(data);
		if (violations.size() > 0) {
			StringBuilder errorMsg = new StringBuilder();

			for (@SuppressWarnings("rawtypes")
			ConstraintViolation violation : violations) {
				errorMsg.append(violation.getMessage()).append("<br/>");

				if (violation.getPropertyPath() != null
						&& !violation.getPropertyPath().toString().equals("")) {
					this.getField(violation.getPropertyPath().toString())
							.addStyleName("errorField");
				} else {
					Annotation validateAnno = violation
							.getConstraintDescriptor().getAnnotation();
					if (validateAnno instanceof DateComparision) {
						String firstDateField = ((DateComparision) validateAnno)
								.firstDateField();
						String lastDateField = ((DateComparision) validateAnno)
								.lastDateField();

						this.getField(firstDateField)
								.addStyleName("errorField");
						this.getField(lastDateField).addStyleName("errorField");
					}
				}

			}

			MessageBox mb = new MessageBox(AppContext.getApplication()
					.getMainWindow(),
					LocalizationHelper
							.getMessage(GenericI18Enum.ERROR_WINDOW_TITLE),
					MessageBox.Icon.ERROR, errorMsg.toString(),
					new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
			mb.show();

			return false;
		}

		return true;
	}

	@Override
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
