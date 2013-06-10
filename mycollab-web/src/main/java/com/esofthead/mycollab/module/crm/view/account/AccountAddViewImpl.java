package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Layout;

@ViewComponent
public class AccountAddViewImpl extends AbstractView implements AccountAddView {

	private class EditForm extends AdvancedEditBeanForm<Account> {
		class FormLayoutFactory extends AccountFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((account.getId() == null) ? "Create Account" : account
						.getAccountname());
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
			}

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<Account>(EditForm.this))
						.createButtonControls();
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}
		}

		private static final long serialVersionUID = 1L;

		private boolean isCheckToCopy;

		@Override
		public void setItemDataSource(Item newDataSource) {
			setFormLayoutFactory(new FormLayoutFactory());
			setFormFieldFactory(new AccountEditFormFieldFactory(account));
			super.setItemDataSource(newDataSource);
		}
	}

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;

	private Account account;

	public AccountAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Account account) {
		this.account = account;
		if (editForm.isCheckToCopy) {
			editForm.isCheckToCopy = false;
		}
		editForm.setItemDataSource(new BeanItem<Account>(account));
	}

	@Override
	public HasEditFormHandlers<Account> getEditFormHandlers() {
		return editForm;
	}
}
