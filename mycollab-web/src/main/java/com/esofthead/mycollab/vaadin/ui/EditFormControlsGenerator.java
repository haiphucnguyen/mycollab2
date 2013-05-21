package com.esofthead.mycollab.vaadin.ui;

import java.io.Serializable;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class EditFormControlsGenerator<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private final AdvancedEditBeanForm<T> editForm;

	public EditFormControlsGenerator(AdvancedEditBeanForm<T> editForm) {
		this.editForm = editForm;
	}

	public HorizontalLayout createButtonControls() {
		return this.createButtonControls(true, true, true);
	}

	public HorizontalLayout createButtonControls(boolean isSaveBtnVisible,
			boolean isSaveAndNewBtnVisible, boolean isCancelBtnVisible) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setStyleName("addNewControl");

		if (isSaveBtnVisible) {
			Button saveBtn = new Button(GenericForm.SAVE_ACTION,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							@SuppressWarnings("unchecked")
							T item = ((BeanItem<T>) editForm
									.getItemDataSource()).getBean();
							if (editForm.validateForm(item)) {
								editForm.fireSaveForm(item);
							}
						}
					});
			saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			layout.addComponent(saveBtn);
			layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);
		}

		if (isSaveAndNewBtnVisible) {
			Button saveAndNewBtn = new Button(GenericForm.SAVE_AND_NEW_ACTION,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							@SuppressWarnings("unchecked")
							T item = ((BeanItem<T>) editForm
									.getItemDataSource()).getBean();
							if (editForm.validateForm(item)) {
								editForm.fireSaveAndNewForm(item);
							}
						}
					});
			saveAndNewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			layout.addComponent(saveAndNewBtn);
			layout.setComponentAlignment(saveAndNewBtn, Alignment.MIDDLE_CENTER);
		}

		if (isCancelBtnVisible) {
			Button cancelBtn = new Button(GenericForm.CANCEL_ACTION,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							editForm.fireCancelForm();
						}
					});
			cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			layout.addComponent(cancelBtn);
			layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
		}

		return layout;
	}
}
