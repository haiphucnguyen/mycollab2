package com.esofthead.mycollab.module.crm.view.account;

import org.vaadin.addon.customfield.FieldWrapper;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class AccountSelectionField extends FieldWrapper<Account> implements
		FieldSelection {

	private HorizontalLayout layout;

	private TextField accountName;

	private SimpleAccount account;

	private Embedded browseBtn;
	private Embedded clearBtn;

	public AccountSelectionField() {
		super(new TextField(""), Account.class);

		layout = new HorizontalLayout();
		layout.setSpacing(true);

		accountName = new TextField();
		accountName.setEnabled(true);
		layout.addComponent(accountName);
		layout.setComponentAlignment(accountName, Alignment.MIDDLE_LEFT);

		browseBtn = new Embedded(null, new ThemeResource(
				"icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

		browseBtn.addListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				AccountSelectionWindow accountWindow = new AccountSelectionWindow(
						AccountSelectionField.this);
				getWindow().addWindow(accountWindow);
				accountWindow.show();

			}
		});

		clearBtn = new Embedded(null, new ThemeResource(
				"icons/16/clearItem.png"));

		clearBtn.addListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				accountName.setValue("");
				AccountSelectionField.this.getWrappedField().setValue(null);
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		this.setCompositionRoot(layout);
		this.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				try {
					AccountService accountService = AppContext
							.getSpringBean(AccountService.class);
					Integer accountId = Integer.parseInt((String) event
							.getProperty().getValue());
					SimpleAccount account = accountService
							.findAccountById(accountId);
					if (account != null) {
						accountName.setValue(account.getAccountname());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public void setAccount(SimpleAccount account) {
		this.account = account;
		accountName.setValue(account.getAccountname());
	}

	@Override
	public void fireValueChange(Object data) {
		account = (SimpleAccount) data;
		if (account != null) {
			accountName.setValue(account.getAccountname());
			this.getWrappedField().setValue(account.getId());
		}

	}

}
