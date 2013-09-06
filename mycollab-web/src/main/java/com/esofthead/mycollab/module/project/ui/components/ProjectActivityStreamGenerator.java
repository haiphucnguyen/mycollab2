package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.utils.AuditLogShowHandler;

public class ProjectActivityStreamGenerator {
	private static AuditLogShowHandler bugHandler = new BugAuditLogShowHandler();
	private static AuditLogShowHandler taskHandler = new TaskAuditLogShowHandler();
	private static AuditLogShowHandler taskListHandler = new TaskListAuditLogShowHandler();
	private static AuditLogShowHandler milestoneHandler = new MilestoneAuditLogShowHandler();
	private static AuditLogShowHandler riskHandler = new RiskAuditLogShowHandler();
	private static AuditLogShowHandler problemHandler = new ProblemAuditLogShowHandler();
	private static AuditLogShowHandler componentHandler = new ComponentAuditLogShowHandler();
	private static AuditLogShowHandler versionHandler = new VersionAuditLogShowHandler();

	private static AuditLogShowHandler defaultHandler = new AuditLogShowHandler();

	private static AuditLogShowHandler getShowHandler(String type) {
		if (ProjectContants.BUG.equals(type)) {
			return bugHandler;
		} else if (ProjectContants.TASK.equals(type)) {
			return taskHandler;
		} else if (ProjectContants.TASK_LIST.equals(type)) {
			return taskListHandler;
		} else if (ProjectContants.MILESTONE.equals(type)) {
			return milestoneHandler;
		} else if (ProjectContants.RISK.equals(type)) {
			return riskHandler;
		} else if (ProjectContants.PROBLEM.equals(type)) {
			return problemHandler;
		} else if (ProjectContants.BUG_COMPONENT.equals(type)) {
			return componentHandler;
		} else if (ProjectContants.BUG_VERSION.equals(type)) {
			return versionHandler;
		} else {
			return defaultHandler;
		}
	}

	public static String generatorDetailChangeOfActivity(
			SimpleActivityStream activityStream) {

		if (activityStream.getAssoAuditLog() != null) {
			AuditLogShowHandler auditLogHandler = getShowHandler(activityStream
					.getType());
			return auditLogHandler.generateChangeSet(activityStream
					.getAssoAuditLog());
		} else {
			return "";
		}
	}

	private static class BugAuditLogShowHandler extends AuditLogShowHandler {
		public BugAuditLogShowHandler() {
			this.generateFieldDisplayHandler("description", "Description");
			this.generateFieldDisplayHandler("environment", "Environment");
			this.generateFieldDisplayHandler("detail", "Detail");
			this.generateFieldDisplayHandler("milestoneName", "Milestone");
			this.generateFieldDisplayHandler("summary", "Summary");
			this.generateFieldDisplayHandler("severity", "Severity");
			this.generateFieldDisplayHandler("priority", "Priority");
			this.generateFieldDisplayHandler("status", "Status");
			this.generateFieldDisplayHandler("duedate", "Due Date",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("resolution", "Resolution");
		}
	}

	private static class TaskAuditLogShowHandler extends AuditLogShowHandler {
		public TaskAuditLogShowHandler() {
			this.generateFieldDisplayHandler("taskname", "Name");
			this.generateFieldDisplayHandler("percentagecomplete",
					"Percentage Complete");
			this.generateFieldDisplayHandler("startdate", "Start Date",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("enddate", "End Date",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("priority", "Priority");
			this.generateFieldDisplayHandler("duration", "Duration");
			this.generateFieldDisplayHandler("isestimated", "Is Estimated");
			this.generateFieldDisplayHandler("deadline", "Deadline",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("actualstartdate",
					"Actual Start Date", AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("actualenddate",
					"Actual End Date", AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("taskListName", LocalizationHelper
					.getMessage(TaskI18nEnum.FORM_TASKGROUP_FIELD));
			this.generateFieldDisplayHandler("assignUserFullName",
					LocalizationHelper
							.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		}
	}

	private static class TaskListAuditLogShowHandler extends
			AuditLogShowHandler {
		public TaskListAuditLogShowHandler() {
			this.generateFieldDisplayHandler("name", "Name");
			this.generateFieldDisplayHandler("status", "Status");
			this.generateFieldDisplayHandler("description", "Description");
			this.generateFieldDisplayHandler("milestoneName", "Milestone");
			this.generateFieldDisplayHandler("ownerFullName",
					LocalizationHelper
							.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		}
	}

	private static class MilestoneAuditLogShowHandler extends
			AuditLogShowHandler {
		public MilestoneAuditLogShowHandler() {
			this.generateFieldDisplayHandler("name", "Name");
			this.generateFieldDisplayHandler("startdate", "Start Date",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("enddate", "End Date",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("status", "Status");
			this.generateFieldDisplayHandler("description", "Description");
			this.generateFieldDisplayHandler("ownerFullName",
					LocalizationHelper
							.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		}
	}

	private static class RiskAuditLogShowHandler extends AuditLogShowHandler {
		public RiskAuditLogShowHandler() {
			this.generateFieldDisplayHandler("riskname", "Name");
			this.generateFieldDisplayHandler("consequence", "Consequence");
			this.generateFieldDisplayHandler("probalitity", "Probability");
			this.generateFieldDisplayHandler("status", "Status");
			this.generateFieldDisplayHandler("dateraised", "Raised Date",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("datedue", "Due Date",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("response", "Response");
			this.generateFieldDisplayHandler("resolution", "Resolution");
			this.generateFieldDisplayHandler("source", "Source");
			this.generateFieldDisplayHandler("description", "Description");
			this.generateFieldDisplayHandler("raisedByUserFullName",
					"Raised By");
			this.generateFieldDisplayHandler("assignedToUserFullName",
					LocalizationHelper
							.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		}
	}

	private static class ProblemAuditLogShowHandler extends AuditLogShowHandler {
		public ProblemAuditLogShowHandler() {
			this.generateFieldDisplayHandler("issuename", "Name");
			this.generateFieldDisplayHandler("impact", "Impact");
			this.generateFieldDisplayHandler("priority", "Priority");
			this.generateFieldDisplayHandler("status", "Status");
			this.generateFieldDisplayHandler("dateraised", "Raised Date",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("datedue", "Due Date",
					AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("actualstartdate",
					"Actual Start Date", AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("actualenddate",
					"Actual End Date", AuditLogShowHandler.DATE_FIELD);
			this.generateFieldDisplayHandler("resolution", "Resolution");
			this.generateFieldDisplayHandler("state", "State");
			this.generateFieldDisplayHandler("problemsource", "Source");
			this.generateFieldDisplayHandler("description", "Description");
			this.generateFieldDisplayHandler("raisedByUserFullName",
					"Raised By");
			this.generateFieldDisplayHandler("assignedUserFullName",
					LocalizationHelper
							.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		}
	}

	private static class ComponentAuditLogShowHandler extends
			AuditLogShowHandler {
		public ComponentAuditLogShowHandler() {
			this.generateFieldDisplayHandler("componentname", "Name");
			this.generateFieldDisplayHandler("description", "Description");
			this.generateFieldDisplayHandler("userLeadFullName", "Lead");
		}
	}

	private static class VersionAuditLogShowHandler extends AuditLogShowHandler {
		public VersionAuditLogShowHandler() {
			this.generateFieldDisplayHandler("duedate", "Due Date");
			this.generateFieldDisplayHandler("versionname", "Name");
			this.generateFieldDisplayHandler("description", "Description");
		}
	}
}
