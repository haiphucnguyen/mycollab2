package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

public class TaskHistoryList extends HistoryLogComponent {
	private static final long serialVersionUID = 1L;

	public TaskHistoryList(int taskId) {
		super(ModuleNameConstants.PRJ, ProjectContants.TASK, taskId);
		this.addStyleName("activity-panel");

		this.generateFieldDisplayHandler("taskname", "Task Name");
		this.generateFieldDisplayHandler("startdate", "Start Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("enddate", "End Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("actualstartdate",
				"Actual Start Date", HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("actualenddate", "Actual End Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("deadline", "Deadline",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("isestimated", "Is Estimated");
		this.generateFieldDisplayHandler("assignuser", LocalizationHelper
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		this.generateFieldDisplayHandler("tasklistid", LocalizationHelper
				.getMessage(TaskI18nEnum.FORM_TASKGROUP_FIELD));
		this.generateFieldDisplayHandler("percentagecomplete", "Complete(%)");
		this.generateFieldDisplayHandler("notes", "Notes");
	}

}
