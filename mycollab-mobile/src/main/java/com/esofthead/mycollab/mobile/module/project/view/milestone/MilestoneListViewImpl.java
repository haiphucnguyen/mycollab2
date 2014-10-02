package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.mobile.ui.AbstractMobileTabPageView;
import com.esofthead.mycollab.mobile.ui.IconConstants;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
@ViewComponent
public class MilestoneListViewImpl extends AbstractMobileTabPageView implements
		MilestoneListView {

	private static final long serialVersionUID = 2799191640785637556L;

	private MilestoneListDisplay closedMilestonesList;
	private MilestoneListDisplay inProgressMilestonesList;
	private MilestoneListDisplay futureMilestonesList;

	public MilestoneListViewImpl() {
		this.closedMilestonesList = new MilestoneListDisplay();
		this.closedMilestonesList.addStyleName("milestones-list");
		this.closedMilestonesList.addStyleName("closed-milestones-list");
		this.addTab(
				closedMilestonesList,
				"<span class=\"nav-btn-icon\" aria-hidden=\"true\" data-icon=\""
						+ IconConstants.PROJECT_MILESTONE_CLOSED
						+ "\"></span><div class=\"screen-reader-text\">"
						+ AppContext
								.getMessage(MilestoneI18nEnum.WIDGET_CLOSED_PHASE_TITLE)
						+ "</div>");

		this.inProgressMilestonesList = new MilestoneListDisplay();
		this.inProgressMilestonesList.addStyleName("milestones-list");
		this.inProgressMilestonesList
				.addStyleName("inprogress-milestones-list");
		this.addTab(
				inProgressMilestonesList,
				"<span class=\"nav-btn-icon\" aria-hidden=\"true\" data-icon=\""
						+ IconConstants.PROJECT_MILESTONE_INPROGRESS
						+ "\"></span><div class=\"screen-reader-text\">"
						+ AppContext
								.getMessage(MilestoneI18nEnum.WIDGET_INPROGRESS_PHASE_TITLE)
						+ "</div>");

		this.futureMilestonesList = new MilestoneListDisplay();
		this.futureMilestonesList.addStyleName("milestones-list");
		this.futureMilestonesList.addStyleName("future-milestones-list");
		this.addTab(
				futureMilestonesList,
				"<span class=\"nav-btn-icon\" aria-hidden=\"true\" data-icon=\""
						+ IconConstants.PROJECT_MILESTONE_FUTURE
						+ "\"></span><div class=\"screen-reader-text\">"
						+ AppContext
								.getMessage(MilestoneI18nEnum.WIDGET_FUTURE_PHASE_TITLE)
						+ "</div>");
	}

	@Override
	public void goToClosedMilestones() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goToInProgressMilestones() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goToFutureMilestones() {
		// TODO Auto-generated method stub

	}

}
