/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class TaskHistoryLogWindow extends HistoryLogWindow {
    public TaskHistoryLogWindow(String module, String type, int typeid) {
        super(module, type, typeid);
        
        this.generateFieldDisplayHandler("taskname", "Task Name");
        this.generateFieldDisplayHandler("startdate", "Start Date", HistoryLogComponent.DATE_FIELD);
        this.generateFieldDisplayHandler("enddate", "End Date", HistoryLogComponent.DATE_FIELD);
        this.generateFieldDisplayHandler("actualstartdate", "Actual Start Date", HistoryLogComponent.DATE_FIELD);
        this.generateFieldDisplayHandler("actualenddate", "Actual End Date", HistoryLogComponent.DATE_FIELD);
        this.generateFieldDisplayHandler("deadline", "Deadline");
        this.generateFieldDisplayHandler("isestimated", "Is Estimated");
        this.generateFieldDisplayHandler("assignuser", "Assign");
        this.generateFieldDisplayHandler("tasklistid", "Task List");
        this.generateFieldDisplayHandler("percentagecomplete", "Complete(%)");
        this.generateFieldDisplayHandler("notes", "Notes");
    }
}
