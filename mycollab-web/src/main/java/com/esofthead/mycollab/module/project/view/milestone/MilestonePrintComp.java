package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class MilestonePrintComp extends AbstractMilestonePreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleMilestone> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleMilestone>();
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return null;
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		final VerticalLayout relatedItemsPanel = new VerticalLayout();
		relatedItemsPanel.setWidth("100%");

		relatedItemsPanel.addComponent(associateCommentListComp);
		relatedItemsPanel.addComponent(associateTaskGroupListComp);
		relatedItemsPanel.addComponent(associateBugListComp);

		return relatedItemsPanel;
	}

}
