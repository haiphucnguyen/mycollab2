package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
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
class CampaignReadComp extends AbstractCampaignPreviewComp {
	private static final long serialVersionUID = 1L;

	private AddViewLayout2 campaignLayout;

	public CampaignReadComp() {
		campaignLayout = new AddViewLayout2("",
				MyCollabResource.newResource("icons/22/crm/account.png"));
		this.addComponent(campaignLayout);

		this.initRelatedComponents();

		previewForm = new AdvancedPreviewBeanForm<SimpleCampaign>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void doPrint() {
				// Create a window that contains what you want to print
				final Window window = new Window("Window to Print");

				final CampaignPrintComp printView = new CampaignPrintComp();
				printView.previewItem(campaign);
				window.setContent(printView);

				UI.getCurrent().addWindow(window);

				// Print automatically when the window opens
				JavaScript.getCurrent().execute(
						"setTimeout(function() {"
								+ "  print(); self.close();}, 0);");
			}

			@Override
			public void showHistory() {
				final CampaignHistoryLogWindow historyLog = new CampaignHistoryLogWindow(
						ModuleNameConstants.CRM, CrmTypeConstants.CAMPAIGN,
						campaign.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};

		VerticalLayout campaignInformation = new VerticalLayout();
		campaignInformation.addStyleName("main-info");
		final Layout actionControls = CrmPreviewFormControlsGenerator
				.createFormButtonControls(previewForm,
						RolePermissionCollections.CRM_CAMPAIGN);
		actionControls.addStyleName("control-buttons");
		campaignInformation.addComponent(actionControls);

		campaignInformation.addComponent(previewForm);
		campaignLayout.addBody(campaignInformation);
		campaignLayout.addBody(createBottomPanel());
	}

	private ComponentContainer createBottomPanel() {
		final TabSheet tabContainer = new TabSheet();
		tabContainer.setWidth("100%");

		tabContainer.addTab(this.noteListItems, "Notes",
				MyCollabResource.newResource("icons/16/crm/note.png"));
		tabContainer.addTab(this.associateAccountList, "Notes",
				MyCollabResource.newResource("icons/16/crm/account.png"));
		tabContainer.addTab(this.associateContactList, "Contacts",
				MyCollabResource.newResource("icons/16/crm/contact.png"));
		tabContainer.addTab(this.associateLeadList, "Leads",
				MyCollabResource.newResource("icons/16/crm/lead.png"));
		return tabContainer;
	}

}
