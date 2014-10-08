package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.module.project.view.parameters.BugFilterParameter;
import com.esofthead.mycollab.mobile.ui.AbstractListPresenter;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class BugListPresenter extends
		AbstractListPresenter<BugListView, BugSearchCriteria, SimpleBug> {

	private static final long serialVersionUID = -3814540725962187693L;

	public BugListPresenter() {
		super(BugListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.BUGS)) {
			BugFilterParameter param = (BugFilterParameter) data.getParams();
			super.onGo(container, data);
			this.doSearch(param.getSearchCriteria());
			AppContext
					.addFragment(
							"project/bug/list/"
									+ GenericLinkUtils
											.encodeParam(new Object[] { CurrentProjectVariables
													.getProjectId() }),
							AppContext.getMessage(BugI18nEnum.VIEW_LIST_TITLE));

		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
