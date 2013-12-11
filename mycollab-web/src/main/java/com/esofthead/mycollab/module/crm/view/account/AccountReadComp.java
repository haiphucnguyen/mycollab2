package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.ui.components.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.vaadin.ui.TabsheetDecor;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
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
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class AccountReadComp extends AbstractAccountPreviewComp {

	private static final long serialVersionUID = 1L;
	private final VerticalLayout accountInformation;
	private final VerticalLayout relatedItemsContainer;
	private final ReadViewLayout accountAddLayout;

	private boolean isLoadedRelateItem = false;

	public AccountReadComp() {
		accountAddLayout = new ReadViewLayout(
				MyCollabResource.newResource("icons/22/crm/account.png"));
		this.addComponent(accountAddLayout);

		initRelatedComponent();

		previewForm = new AdvancedPreviewBeanForm<SimpleAccount>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void doPrint() {
				// Create a window that contains what you want to print
				final Window window = new Window("Window to Print");

				final AbstractAccountPreviewComp printView = new AccountPrintComp();
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
			public void showHistory() {
				final AccountHistoryLogWindow historyLog = new AccountHistoryLogWindow(
						ModuleNameConstants.CRM, CrmTypeConstants.ACCOUNT,
						account.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};

		accountInformation = new VerticalLayout();
		accountInformation.addStyleName("main-info");
		final Layout actionControls = CrmPreviewFormControlsGenerator
				.createFormButtonControls(previewForm,
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
					public void selectedTabChange(SelectedTabChangeEvent event) {
						final Tab tab = ((TabsheetDecor) event.getTabSheet())
								.getSelectedTabInfo();
						final String caption = tab.getCaption();

						if ("More Information".equals(caption)) {
							if (!isLoadedRelateItem) {
								displayActivities();
								associateContactList.displayContacts(account);
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
