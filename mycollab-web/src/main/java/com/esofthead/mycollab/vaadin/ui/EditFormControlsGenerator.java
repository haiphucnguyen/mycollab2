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

import java.io.Serializable;

import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class EditFormControlsGenerator<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private final AdvancedEditBeanForm<T> editForm;

	public EditFormControlsGenerator(final AdvancedEditBeanForm<T> editForm) {
		this.editForm = editForm;
	}

	public HorizontalLayout createButtonControls() {
		return this.createButtonControls(true, true, true);
	}

	public HorizontalLayout createButtonControls(
			final boolean isSaveBtnVisible,
			final boolean isSaveAndNewBtnVisible,
			final boolean isCancelBtnVisible) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setStyleName("addNewControl");

		if (isSaveBtnVisible) {
			final Button saveBtn = new Button(GenericForm.SAVE_ACTION,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							@SuppressWarnings("unchecked")
							final T item = ((BeanItem<T>) EditFormControlsGenerator.this.editForm
									.getItemDataSource()).getBean();
							if (EditFormControlsGenerator.this.editForm
									.validateForm(item)) {
								EditFormControlsGenerator.this.editForm
										.fireSaveForm(item);
							}
						}
					});
			saveBtn.setIcon(MyCollabResource.newResource("icons/16/save.png"));
			saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			layout.addComponent(saveBtn);
			layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);
		}

		if (isSaveAndNewBtnVisible) {
			final Button saveAndNewBtn = new Button(
					GenericForm.SAVE_AND_NEW_ACTION,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							@SuppressWarnings("unchecked")
							final T item = ((BeanItem<T>) EditFormControlsGenerator.this.editForm
									.getItemDataSource()).getBean();
							if (EditFormControlsGenerator.this.editForm
									.validateForm(item)) {
								EditFormControlsGenerator.this.editForm
										.fireSaveAndNewForm(item);
							}
						}
					});
			saveAndNewBtn.setIcon(MyCollabResource
					.newResource("icons/16/save_new.png"));
			saveAndNewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			layout.addComponent(saveAndNewBtn);
			layout.setComponentAlignment(saveAndNewBtn, Alignment.MIDDLE_CENTER);
		}

		if (isCancelBtnVisible) {
			final Button cancelBtn = new Button(GenericForm.CANCEL_ACTION,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							EditFormControlsGenerator.this.editForm
									.fireCancelForm();
						}
					});
			cancelBtn.setIcon(MyCollabResource
					.newResource("icons/16/cancel.png"));
			cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			layout.addComponent(cancelBtn);
			layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
		}

		return layout;
	}
}
