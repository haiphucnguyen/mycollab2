package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.HorizontalLayout;

public class ContactReadViewImpl extends AbstractView implements
		ContactReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

	public ContactReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void displayItem(Contact contact) {
		previewForm.setItemDataSource(new BeanItem<Contact>(contact));
	}

	@Override
	public HasPreviewFormHandlers<Contact> getPreviewFormHandlers() {
		return previewForm;
	}

	private static class PreviewForm extends AdvancedPreviewBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		public PreviewForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
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
