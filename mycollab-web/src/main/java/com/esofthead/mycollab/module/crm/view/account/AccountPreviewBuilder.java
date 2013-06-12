/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator2;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
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
						account.getAssignUserFullName());
			} else if (propertyId.equals("website")) {
				return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
						account.getWebsite());
			}

			return null;
		}
	}

	/**
     *
     */
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
	}

	public static class ReadView extends AccountPreviewBuilder {

		private static final long serialVersionUID = 1L;
		private final VerticalLayout accountInformation;
		private final VerticalLayout relatedItemsContainer;
		private final ReadViewLayout accountAddLayout;

		public ReadView() {
			accountAddLayout = new ReadViewLayout(new ThemeResource(
					"icons/22/crm/account.png"));
			this.addComponent(accountAddLayout);

			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<Account>() {
				private static final long serialVersionUID = 1L;

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					final Window window = new Window("Window to Print");

					final AccountPreviewBuilder printView = new AccountPreviewBuilder.PrintView();
					printView.previewItem(account);
					window.addComponent(printView);

					// Add the printing window as a new application-level window
					getApplication().addWindow(window);

					// Open it as a popup window with no decorations
					getWindow().open(new ExternalResource(window.getURL()),
							"_blank", 1100, 200, // Width and height
							Window.BORDER_NONE); // No decorations

					// Print automatically when the window opens.
					// This call will block until the print dialog exits!
					window.executeJavaScript("print();");

					// Close the window automatically after printing
					window.executeJavaScript("self.close();");
				}

				@Override
				public void setItemDataSource(final Item newDataSource) {
					setFormLayoutFactory(new AccountFormLayoutFactory.AccountInformationLayout());
					setFormFieldFactory(new AccountFormFieldFactory());
					super.setItemDataSource(newDataSource);
					accountAddLayout.setTitle(account.getAccountname());
				}

				@Override
				protected void showHistory() {
					final AccountHistoryLogWindow historyLog = new AccountHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.ACCOUNT,
							account.getId());
					getWindow().addWindow(historyLog);
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
			accountAddLayout.addTab(relatedItemsContainer, "More Information");

			this.addComponent(accountAddLayout);

			accountAddLayout
					.addTabChangedListener(new DetachedTabs.TabChangedListener() {

						@Override
						public void tabChanged(final TabChangedEvent event) {
							final Button btn = event.getSource();
							final String caption = btn.getCaption();
							if ("Account Information".equals(caption)) {

							} else if ("More Information".equals(caption)) {
								relatedItemsContainer.addComponent(associateActivityList);
								relatedItemsContainer.addComponent(associateContactList);
								relatedItemsContainer.addComponent(associateOpportunityList);
								relatedItemsContainer.addComponent(associateCaseList);
								relatedItemsContainer.addComponent(associateLeadList);
							}
							accountAddLayout.selectTab(caption);
						}
					});
		}
	}

	public void displayActivities() {
		final EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.ACCOUNT));
		criteria.setTypeid(new NumberSearchField(account.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	private void displayAssociateCaseList() {
		final CaseSearchCriteria criteria = new CaseSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(account.getId()));
		associateCaseList.setSearchCriteria(criteria);
	}

	private void displayAssociateLeadList() {
		associateLeadList.displayLeads(account);
	}

	private void displayAssociateOpportunityList() {
		final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, account
				.getId()));
		associateOpportunityList.setSearchCriteria(criteria);
	}

	private void displayNotes() {
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
		previewForm.setItemDataSource(new BeanItem<Account>(account));

		displayNotes();
		displayActivities();
		associateContactList.displayContacts(account);
		displayAssociateCaseList();
		displayAssociateOpportunityList();
		displayAssociateLeadList();
	}
}
