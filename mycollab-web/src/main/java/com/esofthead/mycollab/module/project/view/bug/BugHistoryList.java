/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class BugHistoryList extends Depot {

    private HistoryLogComponent historyLogComponent;

    public BugHistoryList(int bugId) {
        super("History", new VerticalLayout());
        this.addStyleName("activity-panel");

        VerticalLayout contentContainer = (VerticalLayout) this.bodyContent;
        historyLogComponent = new HistoryLogComponent(ModuleNameConstants.PRJ, ProjectContants.BUG, bugId);
        contentContainer.addComponent(historyLogComponent);
        
        historyLogComponent.generateFieldDisplayHandler("description", "Description");
        historyLogComponent.generateFieldDisplayHandler("summary", "Summary");
        historyLogComponent.generateFieldDisplayHandler("detail", "Detail");
        historyLogComponent.generateFieldDisplayHandler("status", "Status");
        historyLogComponent.generateFieldDisplayHandler("priority", "Priority");
        historyLogComponent.generateFieldDisplayHandler("severity", "Severity");
        historyLogComponent.generateFieldDisplayHandler("resolution", "Resolution");
        historyLogComponent.generateFieldDisplayHandler("duedate", "Due Date", HistoryLogComponent.DATE_FIELD);
        historyLogComponent.generateFieldDisplayHandler("createdTime", "Created Time", HistoryLogComponent.DATE_FIELD);
        historyLogComponent.generateFieldDisplayHandler("loguserFullName", "Logged by");
        historyLogComponent.generateFieldDisplayHandler("assignuserFullName", "Assigned to");
        historyLogComponent.generateFieldDisplayHandler("assignuser", "Assigned to");
        historyLogComponent.generateFieldDisplayHandler("milestoneid", "Milestone");
    }
}
