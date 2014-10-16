package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.module.project.view.task.TaskListDisplay;
import com.esofthead.mycollab.mobile.ui.AbstractRelatedListView;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class MilestoneRelatedTaskView extends
		AbstractRelatedListView<SimpleTask, TaskSearchCriteria> {

	private static final long serialVersionUID = 5053569495583750804L;

	private SimpleMilestone milestone;

	public MilestoneRelatedTaskView() {
		this.setCaption(AppContext
				.getMessage(TaskI18nEnum.M_VIEW_RELATED_TITLE));
		itemList = new TaskListDisplay();
		this.setContent(itemList);
	}

	@Override
	public void refresh() {
		loadTasks();
	}

	public void displayTasks(SimpleMilestone targetMilestone) {
		this.milestone = targetMilestone;
		loadTasks();
	}

	private void loadTasks() {
		TaskSearchCriteria criteria = new TaskSearchCriteria();
		criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setMilestoneId(new NumberSearchField(milestone.getId()));
		setSearchCriteria(criteria);
	}

	@Override
	protected Component createRightComponent() {
		return null;
	}

}
