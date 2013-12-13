package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class AccountPrintComp extends AbstractAccountPreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleAccount> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleAccount>();
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return null;
	}

	@Override
	protected ComponentContainer createBottomPanel() {
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

}
