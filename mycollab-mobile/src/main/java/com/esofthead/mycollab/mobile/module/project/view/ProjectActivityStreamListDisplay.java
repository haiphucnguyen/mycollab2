package com.esofthead.mycollab.mobile.module.project.view;

import java.util.List;

import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.service.ProjectActivityStreamService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class ProjectActivityStreamListDisplay
		extends
		AbstractPagedBeanList<ActivityStreamSearchCriteria, ProjectActivityStream> {

	private static final long serialVersionUID = 9189667863722393067L;
	protected final ProjectActivityStreamService projectActivityStreamService;

	public ProjectActivityStreamListDisplay() {
		super(null, 20);
		this.projectActivityStreamService = ApplicationContextUtil
				.getSpringBean(ProjectActivityStreamService.class);
	}

	@Override
	protected int queryTotalCount() {
		return projectActivityStreamService
				.getTotalActivityStream(searchRequest.getSearchCriteria());
	}

	@Override
	protected List<ProjectActivityStream> queryCurrentData() {
		return projectActivityStreamService
				.getProjectActivityStreams(searchRequest);
	}

}
