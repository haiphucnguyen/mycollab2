package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.ui.components.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab
 * @since 3.0
 * 
 */
class ContactReadComp extends AbstractContactPreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected ComponentContainer createBottomPanel() {
		final TabSheet tabContainer = new TabSheet();
		tabContainer.setWidth("100%");

		tabContainer.addTab(this.noteListItems, "Notes",
				MyCollabResource.newResource("icons/16/crm/note.png"));
		tabContainer.addTab(this.associateActivityList, "Activities",
				MyCollabResource.newResource("icons/16/crm/calendar.png"));
		tabContainer.addTab(this.associateOpportunityList, "Opportunities",
				MyCollabResource.newResource("icons/16/crm/opportunity.png"));
		return tabContainer;
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleContact> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleContact>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void doPrint() {
				// Create a window that contains what you want to print
				final Window window = new Window("Window to Print");

				final ContactPrintComp printView = new ContactPrintComp();
				printView.previewItem(beanItem);
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
						beanItem.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return CrmPreviewFormControlsGenerator.createFormButtonControls(
				previewForm, RolePermissionCollections.CRM_CONTACT);
	}
}
