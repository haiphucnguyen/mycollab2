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

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.BeanItemCustomExt;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanCustomForm;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.TabsheetDecor;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.UserLinkViewField;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator2;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class AccountPreviewBuilder extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	protected SimpleAccount account;
	protected AdvancedPreviewBeanForm<Account> previewForm;
	protected AccountContactListComp associateContactList;
	protected AccountOpportunityListComp associateOpportunityList;
	protected AccountLeadListComp associateLeadList;

	protected AccountCaseListComp associateCaseList;

	protected EventRelatedItemListComp associateActivityList;

	protected NoteListItems noteListItems;

	protected class AccountFormFieldFactory extends DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(final Item item, final Object propertyId,
				final Component uiContext) {
			if (propertyId.equals("email")) {
				return new DefaultFormViewFieldFactory.FormEmailLinkViewField(
						account.getEmail());
			} else if (propertyId.equals("assignuser")) {
				return new UserLinkViewField(account.getAssignuser(),
						account.getAssignUserAvatarId(),
						account.getAssignUserFullName());
			} else if (propertyId.equals("website")) {
				return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
						account.getWebsite());
			}

			return null;
		}
	}

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

	public AdvancedPreviewBeanForm<Account> getPreviewForm() {
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
		previewForm.setItemDataSource(new BeanItemCustomExt<SimpleAccount>(
				account));
		displayNotes();
	}

	public static class PrintView extends AccountPreviewBuilder {
		class FormLayoutFactory extends AccountFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(account.getAccountname());
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel.addComponent(noteListItems);
				relatedItemsPanel.addComponent(associateActivityList);
				relatedItemsPanel.addComponent(associateContactList);
				relatedItemsPanel.addComponent(associateOpportunityList);
				relatedItemsPanel.addComponent(associateCaseList);
				relatedItemsPanel.addComponent(associateLeadList);

				return relatedItemsPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}
		}

		private static final long serialVersionUID = 1L;

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<Account>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setItemDataSource(final Item newDataSource) {
					setFormLayoutFactory(new FormLayoutFactory());
					setFormFieldFactory(new AccountFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			initRelatedComponent();

			this.addComponent(previewForm);
		}

		@Override
		public void previewItem(SimpleAccount item) {
			super.previewItem(item);
			displayActivities();
			associateContactList.displayContacts(account);
			displayAssociateCaseList();
			displayAssociateOpportunityList();
			displayAssociateLeadList();
		}

	}

	public static class ReadView extends AccountPreviewBuilder {

		private static final long serialVersionUID = 1L;
		private final VerticalLayout accountInformation;
		private final VerticalLayout relatedItemsContainer;
		private final ReadViewLayout accountAddLayout;

		private boolean isLoadedRelateItem = false;

		public ReadView() {
			accountAddLayout = new ReadViewLayout(
					MyCollabResource.newResource("icons/22/crm/account.png"));
			this.addComponent(accountAddLayout);

			initRelatedComponent();

			DynaFormLayout formLayout = new DynaFormLayout(
					CrmTypeConstants.ACCOUNT,
					AccountDefaultDynaFormFactory.getForm());

			previewForm = new AdvancedPreviewBeanCustomForm<Account>(formLayout) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					final Window window = new Window("Window to Print");

					final AccountPreviewBuilder printView = new AccountPreviewBuilder.PrintView();
					printView.previewItem(account);
					window.setContent(printView);

					UI.getCurrent().addWindow(window);

					// Print automatically when the window opens
					JavaScript.getCurrent().execute(
							"setTimeout(function() {"
									+ "  print(); self.close();}, 0);");
				}

				@Override
				public void setItemDataSource(final Item newDataSource) {
					super.setItemDataSource(newDataSource);
					accountAddLayout.setTitle(account.getAccountname());
				}

				@Override
				protected void showHistory() {
					final AccountHistoryLogWindow historyLog = new AccountHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.ACCOUNT,
							account.getId());
					UI.getCurrent().addWindow(historyLog);
				}

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("email")) {
						return new DefaultFormViewFieldFactory.FormEmailLinkViewField(
								account.getEmail());
					} else if (propertyId.equals("assignuser")) {
						return new UserLinkViewField(account.getAssignuser(),
								account.getAssignUserAvatarId(),
								account.getAssignUserFullName());
					} else if (propertyId.equals("website")) {
						return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
								account.getWebsite());
					}

					return null;
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_ACCOUNT);

			accountAddLayout.addControlButtons(optionalActionControls);

			accountInformation = new VerticalLayout();
			accountInformation.addStyleName("main-info");
			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_ACCOUNT);
			actionControls.addStyleName("control-buttons");
			accountInformation.addComponent(actionControls);

			accountInformation.addComponent(previewForm);
			accountInformation.addComponent(noteListItems);

			accountAddLayout.addTab(accountInformation, "Account Information");

			relatedItemsContainer = new VerticalLayout();
			relatedItemsContainer.setMargin(true);
			relatedItemsContainer.addComponent(associateActivityList);
			relatedItemsContainer.addComponent(associateContactList);
			relatedItemsContainer.addComponent(associateOpportunityList);
			relatedItemsContainer.addComponent(associateCaseList);
			relatedItemsContainer.addComponent(associateLeadList);

			accountAddLayout.addTab(relatedItemsContainer, "More Information");

			this.addComponent(accountAddLayout);

			accountAddLayout
					.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void selectedTabChange(
								SelectedTabChangeEvent event) {
							final Tab tab = ((TabsheetDecor) event
									.getTabSheet()).getSelectedTabInfo();
							final String caption = tab.getCaption();

							if ("More Information".equals(caption)) {
								if (!isLoadedRelateItem) {
									displayActivities();
									associateContactList
											.displayContacts(account);
									displayAssociateCaseList();
									displayAssociateOpportunityList();
									displayAssociateLeadList();
									isLoadedRelateItem = true;
								}

							}

						}
					});
		}

		@Override
		public void previewItem(SimpleAccount item) {
			super.previewItem(item);
			isLoadedRelateItem = false;
			accountAddLayout.selectTab("Account Information");
		}

	}

}
