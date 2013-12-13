package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class CasePrintComp extends AbstractCasePreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleCase> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleCase>();
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
		relatedItemsPanel.addComponent(associateContactList);

		return relatedItemsPanel;
	}

}
