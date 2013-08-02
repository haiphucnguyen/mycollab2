/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class BugHistoryList extends HistoryLogComponent {

	public BugHistoryList(int bugId) {
		super(ModuleNameConstants.PRJ, ProjectContants.BUG, bugId);
		this.addStyleName("activity-panel");

		this.generateFieldDisplayHandler("description", "Description");
		this.generateFieldDisplayHandler("environment", "Environment");
		this.generateFieldDisplayHandler("summary", "Summary");
		this.generateFieldDisplayHandler("detail", "Detail");
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("priority", "Priority");
		this.generateFieldDisplayHandler("severity", "Severity");
		this.generateFieldDisplayHandler("resolution", "Resolution");
		this.generateFieldDisplayHandler("duedate", "Due Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("createdTime", "Created Time",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("loguserFullName", "Logged by");
		this.generateFieldDisplayHandler("assignuserFullName", "Assigned to");
		this.generateFieldDisplayHandler("assignuser", "Assigned to");
		this.generateFieldDisplayHandler("milestoneid", "Phase");
	}
}
