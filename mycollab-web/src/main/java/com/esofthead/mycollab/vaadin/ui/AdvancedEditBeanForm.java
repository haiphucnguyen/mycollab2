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

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;

/**
 * Generic attachForm with java bean as datasource. It includes validation
 * against bean input
 * 
 * @param <B>
 *            java bean as datasource map with attachForm fields
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class AdvancedEditBeanForm<B> extends GenericBeanForm<B> implements
		HasEditFormHandlers<B> {
	private static final long serialVersionUID = 1L;

	private final Validator validation;
	private List<EditFormHandler<B>> editFormHandlers;

	public AdvancedEditBeanForm() {
		validation = ApplicationContextUtil
				.getSpringBean(LocalValidatorFactoryBean.class);
	}

	/**
	 * Validate attachForm against data
	 * 
	 * @return true if data is valid, otherwise return false and show result to
	 *         attachForm
	 */
	public boolean validateForm() {

		// for (Object propertyId : this.getItemPropertyIds()) {
		// this.getField(propertyId).removeStyleName("errorField");
		// }
		// Set<ConstraintViolation<Object>> violations =
		// validation.validate(data);
		// if (violations.size() > 0) {
		// StringBuilder errorMsg = new StringBuilder();
		//
		// for (@SuppressWarnings("rawtypes")
		// ConstraintViolation violation : violations) {
		// errorMsg.append(violation.getMessage()).append("<br/>");
		//
		// if (violation.getPropertyPath() != null
		// && !violation.getPropertyPath().toString().equals("")) {
		// this.getField(violation.getPropertyPath().toString())
		// .addStyleName("errorField");
		// } else {
		// Annotation validateAnno = violation
		// .getConstraintDescriptor().getAnnotation();
		// if (validateAnno instanceof DateComparision) {
		// String firstDateField = ((DateComparision) validateAnno)
		// .firstDateField();
		// String lastDateField = ((DateComparision) validateAnno)
		// .lastDateField();
		//
		// this.getField(firstDateField)
		// .addStyleName("errorField");
		// this.getField(lastDateField).addStyleName("errorField");
		// }
		// }
		//
		// }
		//
		// NotificationUtil.showErrorNotification(errorMsg.toString());
		//
		// return false;
		// }
		//
		// return true;
		return true;
	}

	@Override
	public void addFormHandler(EditFormHandler<B> editFormHandler) {
		if (editFormHandlers == null) {
			editFormHandlers = new ArrayList<EditFormHandler<B>>();
		}

		editFormHandlers.add(editFormHandler);
	}

	protected void fireSaveForm() {
		fieldFactory.commit();
		if (editFormHandlers != null) {
			for (EditFormHandler<B> editFormHandler : editFormHandlers) {
				editFormHandler.onSave(this.getBean());
			}
		}
	}

	protected void fireSaveAndNewForm() {
		if (editFormHandlers != null) {
			for (EditFormHandler<B> editFormHandler : editFormHandlers) {
				editFormHandler.onSaveAndNew(this.getBean());
			}
		}
	}

	protected void fireCancelForm() {
		if (editFormHandlers != null) {
			for (EditFormHandler<B> editFormHandler : editFormHandlers) {
				editFormHandler.onCancel();
			}
		}
	}
}
