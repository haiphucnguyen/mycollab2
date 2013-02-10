package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.view.task.TaskGroupDisplayWidget;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

public class MilestoneTaskGroupListComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	
	private Milestone milestone;
	private TaskGroupDisplayWidget taskGroupDisplayWidget;
	
	public MilestoneTaskGroupListComp() {
		taskGroupDisplayWidget = new TaskGroupDisplayWidget();
		this.addComponent(taskGroupDisplayWidget);
	}
	
	public void displayTakLists(Milestone milestone) {
		this.milestone = milestone;
		
        TaskListSearchCriteria criteria = new TaskListSearchCriteria();
        SimpleProject project = (SimpleProject) AppContext
                .getVariable(ProjectContants.PROJECT_NAME);
        criteria.setProjectId(new NumberSearchField(project.getId()));
        criteria.setStatus(new StringSearchField("Open"));
        criteria.setMilestoneIds(new SetSearchField<Integer>(milestone.getId()));
        
        taskGroupDisplayWidget.setSearchCriteria(criteria);
    }

}
