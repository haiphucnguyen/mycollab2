package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
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
 * @author MyCollab
 * @since 3.0
 * 
 */
class ContactReadComp extends AbstractContactPreviewComp {
	private static final long serialVersionUID = 1L;

	private AddViewLayout2 contactAddLayout;

	public ContactReadComp() {
		contactAddLayout = new AddViewLayout2("",
				MyCollabResource.newResource("icons/22/crm/contact.png"));
		this.addComponent(contactAddLayout);

		this.initRelatedComponent();

		previewForm = new AdvancedPreviewBeanForm<SimpleContact>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void doPrint() {
				// Create a window that contains what you want to print
				final Window window = new Window("Window to Print");

				final ContactPrintComp printView = new ContactPrintComp();
				printView.previewItem(contact);
				window.setContent(printView);

				UI.getCurrent().addWindow(window);

				// Print automatically when the window opens
				JavaScript.getCurrent().execute(
						"setTimeout(function() {"
								+ "  print(); self.close();}, 0);");
			}

			@Override
			public void showHistory() {
				final ContactHistoryLogWindow historyLog = new ContactHistoryLogWindow(
						ModuleNameConstants.CRM, CrmTypeConstants.CONTACT,
						contact.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};

		VerticalLayout contactInformation = new VerticalLayout();
		contactInformation.addStyleName("main-info");
		final Layout actionControls = CrmPreviewFormControlsGenerator
				.createFormButtonControls(previewForm,
						RolePermissionCollections.CRM_ACCOUNT);
		actionControls.addStyleName("control-buttons");
		contactInformation.addComponent(actionControls);

		contactInformation.addComponent(previewForm);
		contactAddLayout.addBody(contactInformation);
		contactAddLayout.addBody(createBottomPanel());
	}

	private ComponentContainer createBottomPanel() {
		final TabSheet tabContainer = new TabSheet();
		tabContainer.setWidth("100%");

		tabContainer.addTab(this.noteListItems, "Notes");
		tabContainer.addTab(this.associateActivityList, "Activities");
		tabContainer.addTab(this.associateOpportunityList, "Opportunities");
		return tabContainer;
	}

	@Override
	public void previewItem(SimpleContact item) {
		super.previewItem(item);
		contactAddLayout.setTitle(item.getContactName());
	}
}
