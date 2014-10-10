package com.esofthead.mycollab.mobile.module.project.view.settings;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.AbstractListPresenter;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class ProjectMemberListPresenter
		extends
		AbstractListPresenter<ProjectMemberListView, ProjectMemberSearchCriteria, SimpleProjectMember> {

	private static final long serialVersionUID = 1L;

	public ProjectMemberListPresenter() {
		super(ProjectMemberListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.USERS)) {
			ProjectMemberSearchCriteria criteria = null;
			if (data.getParams() == null) {
				criteria = new ProjectMemberSearchCriteria();
				criteria.setProjectId(new NumberSearchField(
						CurrentProjectVariables.getProjectId()));
				criteria.setStatus(new StringSearchField(
						ProjectMemberStatusConstants.ACTIVE));
				criteria.setSaccountid(new NumberSearchField(AppContext
						.getAccountId()));
			} else {
				criteria = (ProjectMemberSearchCriteria) data.getParams();
			}
			super.onGo(container, data);
			doSearch(criteria);
			AppContext
					.addFragment(
							"project/user/list/"
									+ GenericLinkUtils
											.encodeParam(new Object[] { CurrentProjectVariables
													.getProjectId() }),
							AppContext
									.getMessage(ProjectMemberI18nEnum.VIEW_LIST_TITLE));

		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
