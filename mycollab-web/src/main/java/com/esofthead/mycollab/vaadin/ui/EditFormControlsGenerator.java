package com.esofthead.mycollab.vaadin.ui;

import java.io.Serializable;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class EditFormControlsGenerator<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private AdvancedEditBeanForm<T> editForm;

	public EditFormControlsGenerator(AdvancedEditBeanForm<T> editForm) {
		this.editForm = editForm;
	}

	public HorizontalLayout createButtonControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setStyleName("addNewControl");
		Button saveBtn = new Button(GenericForm.SAVE_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						@SuppressWarnings("unchecked")
						T item = ((BeanItem<T>) editForm.getItemDataSource())
								.getBean();
						if (editForm.validateForm(item)) {
							editForm.fireSaveForm(item);
						}
					}
				});
		layout.addComponent(saveBtn);
		layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

		Button saveAndNewBtn = new Button(GenericForm.SAVE_AND_NEW_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						@SuppressWarnings("unchecked")
						T item = ((BeanItem<T>) editForm.getItemDataSource())
								.getBean();
						if (editForm.validateForm(item)) {
							editForm.fireSaveAndNewForm(item);
						}
					}
				});

		layout.addComponent(saveAndNewBtn);
		layout.setComponentAlignment(saveAndNewBtn, Alignment.MIDDLE_CENTER);

		Button cancelBtn = new Button(GenericForm.CANCEL_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						editForm.fireCancelForm();
					}
				});

		layout.addComponent(cancelBtn);
		layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
		return layout;
	}
}
