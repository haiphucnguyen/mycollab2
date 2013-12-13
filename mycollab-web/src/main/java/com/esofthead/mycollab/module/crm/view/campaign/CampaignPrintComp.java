package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class CampaignPrintComp extends AbstractCampaignPreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleCampaign> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleCampaign>();
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return null;
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		VerticalLayout relatedItemsPanel = new VerticalLayout();
		relatedItemsPanel.setWidth("100%");

		relatedItemsPanel.addComponent(noteListItems);
		relatedItemsPanel.addComponent(associateActivityList);
		relatedItemsPanel.addComponent(associateAccountList);
		relatedItemsPanel.addComponent(associateContactList);
		relatedItemsPanel.addComponent(associateLeadList);

		return relatedItemsPanel;
	}

}
