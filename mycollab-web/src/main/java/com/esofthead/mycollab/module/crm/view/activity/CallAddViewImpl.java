package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Collection;

import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public class CallAddViewImpl extends AbstractView implements CallAddView {
	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	private Call task;

	public CallAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Call item) {
		this.task = item;
		editForm.setItemDataSource(new BeanItem<Call>(task));
	}

	private class EditForm extends AdvancedEditBeanForm<Call> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource,
				Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends CallFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<Call>(EditForm.this))
						.createButtonControls();
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Call> getEditFormHandlers() {
		return editForm;
	}

}
