/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.mobile.module.crm.ui.AbstractRelatedListView;
import com.esofthead.mycollab.mobile.module.crm.view.contact.ContactListDisplay;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.vaadin.AppContext;

public class AccountRelatedContactView extends AbstractRelatedListView<SimpleContact, ContactSearchCriteria> {
	private static final long serialVersionUID = 6290597056477524107L;
	private Account account;

	public AccountRelatedContactView() {
		initUI();
	}

	public void displayContacts(final Account account) {
		this.account = account;
		loadContacts();
	}

	private void initUI() {
		this.setCaption("Related Contacts");
		this.tableItem = new ContactListDisplay("contactName");
		this.setContent(tableItem);
	}

	private void loadContacts() {
		final ContactSearchCriteria criteria = new ContactSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, account
				.getId()));
		setSearchCriteria(criteria);
	}

	@Override
	public void refresh() {
		loadContacts();
	}

}
