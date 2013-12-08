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

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class AccountSelectionField extends CustomField<Account> implements
		FieldSelection {

	private TextField accountName;
	private SimpleAccount account = new SimpleAccount();
	private Embedded browseBtn;
	private Embedded clearBtn;

	public void clearValue() {
		accountName.setValue("");
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
		}

	}

	@Override
	protected Component initContent() {
		HorizontalLayout layout = new HorizontalLayout();
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

		browseBtn.addClickListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				AccountSelectionWindow accountWindow = new AccountSelectionWindow(
						AccountSelectionField.this);
				UI.getCurrent().addWindow(accountWindow);
				accountWindow.show();
			}
		});

		clearBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));

		clearBtn.addClickListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				clearValue();
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		layout.setExpandRatio(accountName, 1.0f);

		return layout;
	}

	@Override
	public Class<? extends Account> getType() {
		return Account.class;
	}
}
