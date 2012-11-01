package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.addon.customfield.FieldWrapper;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class AccountSelectionField extends FieldWrapper<Account> implements
		FieldSelection {

	private HorizontalLayout layout;

	private TextField accountName;
	private Embedded browseBtn;
	private Embedded clearBtn;

	@Autowired
	private AccountService accountService;

	public AccountSelectionField() {
		super(new TextField(""), Account.class);

		layout = new HorizontalLayout();
		layout.setSpacing(true);

		accountName = new TextField();
		layout.addComponent(accountName);

		browseBtn = new Embedded("", new ThemeResource(
				"icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		browseBtn.addListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				AccountSelectionWindow accountWindow = new AccountSelectionWindow(
						AccountSelectionField.this);
				getWindow().addWindow(accountWindow);
				accountWindow.show();

			}
		});

		clearBtn = new Embedded("", new ThemeResource("icons/16/clearItem.png"));
		clearBtn.addListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				accountName.setValue("");
				AccountSelectionField.this.getWrappedField().setValue(null);
			}
		});
		layout.addComponent(clearBtn);

		this.setCompositionRoot(layout);
		this.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				try {
				Integer accountId = Integer.parseInt((String) event
						.getProperty().getValue());
				SimpleAccount account = accountService
						.findAccountById(accountId);
				if (account != null) {
					accountName.setValue(account.getAccountname());
				}
				} catch (Exception e) {
					
				}

			}
		});
	}

	@Override
	public void fireValueChange(Object data) {
		Account account = (Account) data;
		accountName.setValue(account.getAccountname());
		this.getWrappedField().setValue(account.getId());
	}

}
