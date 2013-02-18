package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.view.task.TaskGroupDisplayWidget;
import com.vaadin.ui.VerticalLayout;

public class MilestoneTaskGroupListComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private TaskGroupDisplayWidget taskGroupDisplayWidget;

	public MilestoneTaskGroupListComp() {
		taskGroupDisplayWidget = new TaskGroupDisplayWidget();
		this.addComponent(taskGroupDisplayWidget);
	}

	public void displayTakLists(Milestone milestone) {

		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));
		criteria.setStatus(new StringSearchField("Open"));
		criteria.setMilestoneIds(new SetSearchField<Integer>(milestone.getId()));

		taskGroupDisplayWidget.setSearchCriteria(criteria);
	}

}
