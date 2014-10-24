package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.mobile.module.project.ui.AbstractListViewComp;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */

@ViewComponent
public class ProjectActivityViewImpl
		extends
		AbstractListViewComp<ActivityStreamSearchCriteria, ProjectActivityStream>
		implements ProjectActivityView {

	private static final long serialVersionUID = 6930154745425180819L;

	public ProjectActivityViewImpl() {
		this.setCaption(AppContext
				.getMessage(ProjectCommonI18nEnum.M_VIEW_PROJECT_ACTIVITIES));
		this.addStyleName("project-activities-view");
	}

	@Override
	protected AbstractPagedBeanList<ActivityStreamSearchCriteria, ProjectActivityStream> createBeanTable() {
		return new ProjectActivityStreamListDisplay();
	}

	@Override
	protected Component createRightComponent() {
		return null;
	}

}
