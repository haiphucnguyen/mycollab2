/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.account;

import org.vaadin.addon.customfield.FieldWrapper;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.UIHelper;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class AccountSelectionField extends FieldWrapper<Account> implements
		FieldSelection {

	private final HorizontalLayout layout;
	private final TextField accountName;
	private SimpleAccount account = new SimpleAccount();
	private final Embedded browseBtn;
	private final Embedded clearBtn;

	public AccountSelectionField() {
		super(new TextField(""), Account.class);

		layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");

		accountName = new TextField();
		accountName.setEnabled(true);
		accountName.setWidth("100%");
		layout.addComponent(accountName);
		layout.setComponentAlignment(accountName, Alignment.MIDDLE_LEFT);

		browseBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

		browseBtn.addListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				AccountSelectionWindow accountWindow = new AccountSelectionWindow(
						AccountSelectionField.this);
				UIHelper.addWindowToRoot(AccountSelectionField.this,
						accountWindow);
				accountWindow.show();
			}
		});

		clearBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));

		clearBtn.addListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				clearValue();
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		layout.setExpandRatio(accountName, 1.0f);

		this.setCompositionRoot(layout);
		this.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				try {
					AccountService accountService = ApplicationContextUtil
							.getSpringBean(AccountService.class);

					Integer accountId = Integer.parseInt((String) event
							.getProperty().getValue());
					SimpleAccount account = accountService.findById(accountId,
							AppContext.getAccountId());
					if (account != null) {
						accountName.setValue(account.getAccountname());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public void clearValue() {
		accountName.setValue("");
		AccountSelectionField.this.getWrappedField().setValue(null);
		this.account = new SimpleAccount();
	}

	public void setAccount(SimpleAccount account) {
		this.account = account;
		accountName.setValue(account.getAccountname());
	}

	public SimpleAccount getAccount() {
		return account;
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
