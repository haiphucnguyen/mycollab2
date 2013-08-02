package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

@ViewComponent
public class AccountAddViewImpl extends AbstractView implements AccountAddView {

	private class EditForm extends AdvancedEditBeanForm<Account> {

		class FormLayoutFactory extends AccountFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(
						(AccountAddViewImpl.this.account.getId() == null) ? "Create Account"
								: AccountAddViewImpl.this.account
										.getAccountname());
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Account>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return this.createButtonControls();
			}
		}

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new AccountEditFormFieldFactory(
					AccountAddViewImpl.this.account));
			super.setItemDataSource(newDataSource);
		}
	}

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;

	private Account account;

	public AccountAddViewImpl() {
		super();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final Account account) {
		this.account = account;
		this.editForm.setItemDataSource(new BeanItem<Account>(account));
	}

	@Override
	public HasEditFormHandlers<Account> getEditFormHandlers() {
		return this.editForm;
	}
}
