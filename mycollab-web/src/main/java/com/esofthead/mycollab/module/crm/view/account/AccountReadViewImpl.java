package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.module.crm.view.contact.ContactListComp;
import com.esofthead.mycollab.module.crm.view.lead.LeadListComp;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityListComp;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class AccountReadViewImpl extends AbstractView implements
		AccountReadView {
	private static final long serialVersionUID = 1L;

	private SimpleAccount account;

	private PreviewForm previewForm;

	private ContactListComp associateContactList;

	private OpportunityListComp associateOpportunityList;

	private LeadListComp associateLeadList;

	public AccountReadViewImpl() {
		super();
		previewForm = new PreviewForm();

		associateContactList = new ContactListComp();
		associateOpportunityList = new OpportunityListComp();
		associateLeadList = new LeadListComp();

		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleAccount item) {
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

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("email")) {
						return new FormEmailLinkViewField(account.getEmail());
					} else if (propertyId.equals("assignuser")) {
						return new FormLinkViewField(account
								.getAssignUserFullName(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
										// TODO Auto-generated method stub

									}
								});
					}

					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends AccountFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<Account>(
						PreviewForm.this)).createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();

				relatedItemsPanel.addComponent(new NoteListItems(
						"Notes", "Account", account.getId()));

				relatedItemsPanel.addComponent(associateContactList);
				relatedItemsPanel.addComponent(associateOpportunityList);
				relatedItemsPanel.addComponent(associateLeadList);

				return relatedItemsPanel;
			}
		}
	}

	@Override
	public SimpleAccount getItem() {
		return account;
	}

	@Override
	public IRelatedListHandlers getRelatedContactHandlers() {
		return associateContactList;
	}

	@Override
	public IRelatedListHandlers getRelatedOpportunityHandlers() {
		return associateOpportunityList;
	}

	@Override
	public IRelatedListHandlers getRelatedLeadHandlers() {
		return associateLeadList;
	}

}
