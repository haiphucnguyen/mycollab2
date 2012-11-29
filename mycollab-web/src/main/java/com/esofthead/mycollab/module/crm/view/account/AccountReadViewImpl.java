package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.view.contact.ContactListComp;
import com.esofthead.mycollab.module.crm.view.lead.LeadListComp;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityListComp;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class AccountReadViewImpl extends AbstractView implements
		AccountReadView {
	private static final long serialVersionUID = 1L;

	private Account account;

	private PreviewForm previewForm;

	private ContactListComp associateContactList;

	private OpportunityListComp associateOpportunityList;

	private LeadListComp associateLeadList;

	public AccountReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void displayItem(Account item) {
		account = item;
		previewForm.setItemDataSource(new BeanItem<Account>(account));
		displayAssociateContactList();
		displayAssociateOpportunityList();
		displayAssociateLeadList();
	}

	private void displayAssociateContactList() {
		ContactSearchCriteria criteria = new ContactSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, account
				.getId()));
		associateContactList.setSearchCriteria(criteria);
	}

	private void displayAssociateOpportunityList() {
		OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, account
				.getId()));
		associateOpportunityList.setSearchCriteria(criteria);
	}

	private void displayAssociateLeadList() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountName(new StringSearchField(SearchField.AND, account
				.getAccountname()));
		associateLeadList.setSearchCriteria(criteria);
	}

	@Override
	public HasPreviewFormHandlers<Account> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		public PreviewForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
		}

		class FormLayoutFactory extends AccountFormLayoutFactory {

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<Account>(
						PreviewForm.this)).createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();

				associateContactList = new ContactListComp();
				relatedItemsPanel.addComponent(associateContactList);

				associateOpportunityList = new OpportunityListComp();
				relatedItemsPanel.addComponent(associateOpportunityList);

				associateLeadList = new LeadListComp();
				relatedItemsPanel.addComponent(associateLeadList);

				return relatedItemsPanel;
			}
		}
	}

}
