/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.ui.components;

import com.vaadin.ui.Window;

/**
 *
 * @author haiphucnguyen
 */
public class HistoryLogWindow extends Window{
    
    private String module;
    
    private String type;
    
    private int typeid;
    
    public HistoryLogWindow(String module, String type, int typeid) {
        super("Change Log");
        this.setWidth("600px");
        
        this.module = module;
        this.type = type;
        this.typeid = typeid;
    }
}
