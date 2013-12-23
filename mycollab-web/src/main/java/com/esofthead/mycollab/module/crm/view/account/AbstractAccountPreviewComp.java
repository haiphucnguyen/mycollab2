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

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.ActivityRelatedItemListComp;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 */
abstract class AbstractAccountPreviewComp extends
		AbstractPreviewItemComp<SimpleAccount> {

	private static final long serialVersionUID = 1L;
	protected AccountContactListComp associateContactList;
	protected AccountOpportunityListComp associateOpportunityList;
	protected AccountLeadListComp associateLeadList;
	protected AccountCaseListComp associateCaseList;
	protected ActivityRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	public AbstractAccountPreviewComp() {
		super(MyCollabResource.newResource("icons/22/crm/account.png"));
	}

	protected void displayActivities() {
		final ActivitySearchCriteria criteria = new ActivitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.ACCOUNT));
		criteria.setTypeid(new NumberSearchField(beanItem.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayAssociateCaseList() {
		final CaseSearchCriteria criteria = new CaseSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(beanItem.getId()));
		associateCaseList.setSearchCriteria(criteria);
	}

	protected void displayAssociateLeadList() {
		associateLeadList.displayLeads(beanItem);
	}

	protected void displayAssociateOpportunityList() {
		final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, beanItem
				.getId()));
		associateOpportunityList.setSearchCriteria(criteria);
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.ACCOUNT, beanItem.getId());
	}

	public ActivityRelatedItemListComp getAssociateActivityList() {
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

	@Override
	protected String initFormTitle() {
		return beanItem.getAccountname();
	}

	protected final void initRelatedComponents() {
		associateContactList = new AccountContactListComp();
		associateActivityList = new ActivityRelatedItemListComp(true);
		associateOpportunityList = new AccountOpportunityListComp();
		associateLeadList = new AccountLeadListComp();
		associateCaseList = new AccountCaseListComp();
		noteListItems = new NoteListItems("Notes");
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.ACCOUNT,
				AccountDefaultDynaFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleAccount> initBeanFormFieldFactory() {
		return new AccountReadFormFieldFactory(previewForm);
	}

	@Override
	protected void onPreviewItem() {
		displayNotes();
		displayActivities();
		associateContactList.displayContacts(beanItem);
		displayAssociateCaseList();
		displayAssociateOpportunityList();
		displayAssociateLeadList();
	}
}
