package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public interface MilestoneListView extends PageView {
	public void goToClosedMilestones();

	public void goToInProgressMilestones();

	public void goToFutureMilestones();
}
