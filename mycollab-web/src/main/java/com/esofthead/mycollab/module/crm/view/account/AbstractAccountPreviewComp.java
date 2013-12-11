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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 */
public abstract class AbstractAccountPreviewComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected SimpleAccount account;

	protected AdvancedPreviewBeanForm<SimpleAccount> previewForm;
	protected AccountContactListComp associateContactList;
	protected AccountOpportunityListComp associateOpportunityList;
	protected AccountLeadListComp associateLeadList;
	protected AccountCaseListComp associateCaseList;
	protected EventRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	protected void displayActivities() {
		final EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.ACCOUNT));
		criteria.setTypeid(new NumberSearchField(account.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayAssociateCaseList() {
		final CaseSearchCriteria criteria = new CaseSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(account.getId()));
		associateCaseList.setSearchCriteria(criteria);
	}

	protected void displayAssociateLeadList() {
		associateLeadList.displayLeads(account);
	}

	protected void displayAssociateOpportunityList() {
		final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, account
				.getId()));
		associateOpportunityList.setSearchCriteria(criteria);
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.ACCOUNT, account.getId());
	}

	public SimpleAccount getAccount() {
		return account;
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public AccountCaseListComp getAssociateCaseList() {
		return associateCaseList;
	}

	public AccountContactListComp getAssociateContactList() {
		return associateContactList;
	}

	public AccountLeadListComp getAssociateLeadList() {
		return associateLeadList;
	}

	public AccountOpportunityListComp getAssociateOpportunityList() {
		return associateOpportunityList;
	}

	public AdvancedPreviewBeanForm<SimpleAccount> getPreviewForm() {
		return previewForm;
	}

	protected void initRelatedComponent() {
		associateContactList = new AccountContactListComp();
		associateActivityList = new EventRelatedItemListComp(true);
		associateOpportunityList = new AccountOpportunityListComp();
		associateLeadList = new AccountLeadListComp();
		associateCaseList = new AccountCaseListComp();
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(final SimpleAccount item) {
		account = item;
		previewForm.setFormLayoutFactory(new DynaFormLayout(
				CrmTypeConstants.ACCOUNT, AccountDefaultDynaFormFactory
						.getForm()));
		previewForm.setBeanFormFieldFactory(new AccountReadFormFieldFactory(
				previewForm));
		previewForm.setBean(item);
		displayNotes();
	}
}
