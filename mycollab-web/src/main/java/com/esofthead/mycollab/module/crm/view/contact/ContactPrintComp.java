package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class ContactPrintComp extends AbstractContactPreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleContact> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleContact>();
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
		relatedItemsPanel.addComponent(associateOpportunityList);

		return relatedItemsPanel;
	}
}
