package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.AbstractMobilePresenter;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class MilestoneListPresenter extends
		AbstractMobilePresenter<MilestoneListView> {

	private static final long serialVersionUID = 8282868336211950427L;

	public MilestoneListPresenter() {
		super(MilestoneListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.MILESTONES)) {

			super.onGo(container, data);
			view.goToInProgressMilestones();

			AppContext
					.addFragment(
							"project/milestone/list/"
									+ GenericLinkUtils
											.encodeParam(new Object[] { CurrentProjectVariables
													.getProjectId() }),
							AppContext
									.getMessage(MilestoneI18nEnum.VIEW_LIST_TITLE));

		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

}
