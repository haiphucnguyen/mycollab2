package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.common.domain.AuditLogShowHandler;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.module.project.ProjectContants;

public class ProjectActivityStreamGenerator {
	private static AuditLogShowHandler bugHandler = new BugAuditLogShowHandler();
	private static AuditLogShowHandler defaultHandler = new AuditLogShowHandler();

	private static AuditLogShowHandler getShowHandler(String type) {
		if (ProjectContants.BUG.equals(type)) {
			return bugHandler;
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
}
