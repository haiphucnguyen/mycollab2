package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.ui.components.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
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

	private AddViewLayout2 accountAddLayout;

	public AccountReadComp() {
		accountAddLayout = new AddViewLayout2("",
				MyCollabResource.newResource("icons/22/crm/account.png"));
		this.addComponent(accountAddLayout);

		initRelatedComponents();

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
			public void showHistory() {
				final AccountHistoryLogWindow historyLog = new AccountHistoryLogWindow(
						ModuleNameConstants.CRM, CrmTypeConstants.ACCOUNT,
						account.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};

		VerticalLayout accountInformation = new VerticalLayout();
		accountInformation.addStyleName("main-info");
		final Layout actionControls = CrmPreviewFormControlsGenerator
				.createFormButtonControls(previewForm,
						RolePermissionCollections.CRM_ACCOUNT);
		actionControls.addStyleName("control-buttons");
		accountInformation.addComponent(actionControls);

		accountInformation.addComponent(previewForm);
		accountAddLayout.addBody(accountInformation);
		accountAddLayout.addBody(createBottomPanel());
	}

	private ComponentContainer createBottomPanel() {
		final TabSheet tabContainer = new TabSheet();
		tabContainer.setWidth("100%");

		tabContainer.addTab(noteListItems, "Notes",
				MyCollabResource.newResource("icons/16/crm/note.png"));
		tabContainer.addTab(associateContactList, "Contacts",
				MyCollabResource.newResource("icons/16/crm/contact.png"));
		tabContainer.addTab(associateOpportunityList, "Opportunities",
				MyCollabResource.newResource("icons/16/crm/opportunity.png"));
		tabContainer.addTab(associateLeadList, "Leads",
				MyCollabResource.newResource("icons/16/crm/lead.png"));
		tabContainer.addTab(associateCaseList, "Cases",
				MyCollabResource.newResource("icons/16/crm/case.png"));
		tabContainer.addTab(associateActivityList, "Activities",
				MyCollabResource.newResource("icons/16/crm/calendar.png"));
		return tabContainer;
	}

	@Override
	public void previewItem(SimpleAccount item) {
		super.previewItem(item);
		accountAddLayout.setTitle(item.getAccountname());

		displayNotes();
		displayActivities();
		associateContactList.displayContacts(account);
		displayAssociateCaseList();
		displayAssociateOpportunityList();
		displayAssociateLeadList();
	}

}
