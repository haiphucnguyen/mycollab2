package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;

public class ContactReadViewImpl extends AbstractView implements
		ContactReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

	private SimpleContact contact;

	public ContactReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleContact item) {
		this.contact = item;
		previewForm.setItemDataSource(new BeanItem<SimpleContact>(contact));
	}

	@Override
	public HasPreviewFormHandlers<Contact> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("serial")
		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("accountid")) {
						return new FormViewField(contact.getAccountName());
					}

					return null;
				}

			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends ContactFormLayoutFactory {

			@Override
			protected HorizontalLayout createButtonControls() {
				return (new PreviewFormControlsGenerator<Contact>(
						PreviewForm.this)).createButtonControls();
			}
		}
	}

}
