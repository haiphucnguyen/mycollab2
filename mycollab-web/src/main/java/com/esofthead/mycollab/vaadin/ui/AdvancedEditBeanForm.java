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

import com.esofthead.mycollab.validator.constraints.DateComparision;
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


	private List<EditFormHandler<B>> editFormHandlers;

	public AdvancedEditBeanForm() {

	}

	/**
	 * Validate attachForm against data
	 * 
	 * @return true if data is valid, otherwise return false and show result to
	 *         attachForm
	 */
	public boolean validateForm() {
		fieldFactory.commit();
        return isValid();
	}

	@Override
	public void addFormHandler(EditFormHandler<B> editFormHandler) {
		if (editFormHandlers == null) {
			editFormHandlers = new ArrayList<EditFormHandler<B>>();
		}

		editFormHandlers.add(editFormHandler);
	}

	public void fireSaveForm() {
		if (editFormHandlers != null) {
			for (EditFormHandler<B> editFormHandler : editFormHandlers) {
				editFormHandler.onSave(this.getBean());
			}
		}
	}

	public void fireSaveAndNewForm() {
		if (editFormHandlers != null) {
			for (EditFormHandler<B> editFormHandler : editFormHandlers) {
				editFormHandler.onSaveAndNew(this.getBean());
			}
		}
	}

	public void fireCancelForm() {
		if (editFormHandlers != null) {
			for (EditFormHandler<B> editFormHandler : editFormHandlers) {
				editFormHandler.onCancel();
			}
		}
	}
}
