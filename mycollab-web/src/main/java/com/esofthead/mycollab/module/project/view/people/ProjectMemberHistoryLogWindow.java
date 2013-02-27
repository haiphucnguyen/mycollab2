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
public class ProjectMemberHistoryLogWindow extends HistoryLogWindow {
    public ProjectMemberHistoryLogWindow(String module, String type, int typeid) {
        super(module, type, typeid);
        
        this.generateFieldDisplayHandler("username", "User");
        this.generateFieldDisplayHandler("isadmin", "Is Admin");
    }
}
