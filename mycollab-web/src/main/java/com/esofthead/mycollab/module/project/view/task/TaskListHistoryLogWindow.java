/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class TaskListHistoryLogWindow extends HistoryLogWindow {
    public TaskListHistoryLogWindow(String module, String type, int typeid) {
        super(module, type, typeid);
        
        this.generateFieldDisplayHandler("name", "Name");
        this.generateFieldDisplayHandler("description", "Description");
        this.generateFieldDisplayHandler("owner", "Responsible User");
        this.generateFieldDisplayHandler("milestoneid", "Related Milestone");
        this.generateFieldDisplayHandler("percentageComplete", "Progress");
        this.generateFieldDisplayHandler("numOpenTasks", "Number of open tasks");
    }
}
