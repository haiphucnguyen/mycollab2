package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class PreviewFormControlsGenerator<T> {

	private AdvancedPreviewBeanForm<T> previewForm;

	public PreviewFormControlsGenerator(AdvancedPreviewBeanForm<T> editForm) {
		this.previewForm = editForm;
	}

	public HorizontalLayout createButtonControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setStyleName("addNewControl");
		Button editBtn = new Button(GenericForm.EDIT_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						@SuppressWarnings("unchecked")
						T item = ((BeanItem<T>) previewForm
								.getItemDataSource()).getBean();
						previewForm.fireEditForm(item);
					}
				});
		layout.addComponent(editBtn);
		layout.setComponentAlignment(editBtn, Alignment.MIDDLE_CENTER);

		Button deleteBtn = new Button(GenericForm.DELETE_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						@SuppressWarnings("unchecked")
						T item = ((BeanItem<T>) previewForm
								.getItemDataSource()).getBean();
						previewForm.fireDeleteForm(item);
					}
				});

		layout.addComponent(deleteBtn);
		layout.setComponentAlignment(deleteBtn,
				Alignment.MIDDLE_CENTER);

		Button cloneBtn = new Button(GenericForm.CLONE_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						@SuppressWarnings("unchecked")
						T item = ((BeanItem<T>) previewForm
								.getItemDataSource()).getBean();
						previewForm.fireCloneForm(item);
					}
				});

		layout.addComponent(cloneBtn);
		layout.setComponentAlignment(cloneBtn, Alignment.MIDDLE_CENTER);
		return layout;
	}

}
