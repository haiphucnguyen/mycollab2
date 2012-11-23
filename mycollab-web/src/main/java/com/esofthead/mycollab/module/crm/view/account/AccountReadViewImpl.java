package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.HorizontalLayout;

public class AccountReadViewImpl extends AbstractView implements
		AccountReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

	public AccountReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void displayItem(Account account) {
		previewForm.setItemDataSource(new BeanItem<Account>(account));
	}

	@Override
	public HasPreviewFormHandlers<Account> getPreviewFormHandlers() {
		return previewForm;
	}

	private static class PreviewForm extends AdvancedPreviewBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		public PreviewForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
		}

		class FormLayoutFactory extends AccountFormLayoutFactory {

			@Override
			protected HorizontalLayout createButtonControls() {
				return (new PreviewFormControlsGenerator<Account>(
						PreviewForm.this)).createButtonControls();
			}
		}
	}

}
