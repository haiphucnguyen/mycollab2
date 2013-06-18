package com.esofthead.mycollab.module.project.view.user;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTaskCount;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskStatusComponent extends Depot {
	private static final long serialVersionUID = 1L;

	private TreeTable taskTree;

	public TaskStatusComponent() {
		super(LocalizationHelper.getMessage(ProjectCommonI18nEnum.TASKS_TITLE),
				new VerticalLayout());

		taskTree = new TreeTable();
		this.bodyContent.addComponent(taskTree);
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showProjectTasksByStatus() {
		ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
		searchCriteria.setsAccountId(new NumberSearchField(AppContext
				.getAccountId()));
		searchCriteria.setIsOpenned(new SearchField());
		searchCriteria.setAssignUser(new StringSearchField(SearchField.AND,
				AppContext.getUsername()));

		ProjectGenericTaskService prjGenericTaskService = AppContext
				.getSpringBean(ProjectGenericTaskService.class);

		List<ProjectGenericTaskCount> taskCountList = prjGenericTaskService
				.findPagableTaskCountListByCriteria(new SearchRequest<ProjectGenericTaskSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));

		for (ProjectGenericTaskCount taskCount : taskCountList) {
			taskTree.addItem(taskCount);
			taskTree.setItemCaption(taskCount, taskCount.getProjectName());
		}
	}

}