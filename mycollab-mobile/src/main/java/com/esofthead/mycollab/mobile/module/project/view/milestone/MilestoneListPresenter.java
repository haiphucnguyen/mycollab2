package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.AbstractListPresenter;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
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
public class MilestoneListPresenter
		extends
		AbstractListPresenter<MilestoneListView, MilestoneSearchCriteria, SimpleMilestone> {

	private static final long serialVersionUID = 8282868336211950427L;

	public MilestoneListPresenter() {
		super(MilestoneListView.class);
	}

	@Override
	protected void postInitView() {
		// Override to prevent setting up search handlers
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.MILESTONES)) {

			MilestoneSearchCriteria searchCriteria;

			if (data.getParams() == null
					|| !(data.getParams() instanceof MilestoneSearchCriteria)) {
				searchCriteria = new MilestoneSearchCriteria();
				searchCriteria
						.setProjectId(new NumberSearchField(SearchField.AND,
								CurrentProjectVariables.getProjectId()));
			} else {
				searchCriteria = (MilestoneSearchCriteria) data.getParams();
			}
			super.onGo(container, data);
			doSearch(searchCriteria);

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

	@Override
	public void doSearch(MilestoneSearchCriteria searchCriteria) {
		view.getPagedBeanTable().setDisplayNumItems(Integer.MAX_VALUE);
		super.doSearch(searchCriteria);
	}

}
