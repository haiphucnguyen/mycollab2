/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class ProjectRoleHistoryLogWindow extends HistoryLogWindow {
    public ProjectRoleHistoryLogWindow(String module, String type, int typeid) {
        super(module, type, typeid);
        
        this.generateFieldDisplayHandler("rolename", "Role Name");
        this.generateFieldDisplayHandler("description", "Description");
    }
}
