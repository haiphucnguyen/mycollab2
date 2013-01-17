/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Window;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class HistoryLogWindow extends Window {
    
    private HistoryLogComponent historyLogComponent;

    public HistoryLogWindow(String module, String type, int typeid) {
        super("Change Log");
        this.setWidth("700px");

        historyLogComponent = new HistoryLogComponent(module, type, typeid);
    }

    @Override
    public void attach() {
        super.attach();
        this.addComponent(new LazyLoadWrapper(historyLogComponent));
        center();
    }
    
    public void generateFieldDisplayHandler(String fieldname, String displayName) {
        historyLogComponent.generateFieldDisplayHandler(fieldname, displayName);
    }

    public void generateFieldDisplayHandler(String fieldname, String displayName, HistoryLogComponent.HistoryFieldFormat format) {
        historyLogComponent.generateFieldDisplayHandler(fieldname, displayName, format);
    }

    public void generateFieldDisplayHandler(String fieldname, String displayName, String formatName) {
        historyLogComponent.generateFieldDisplayHandler(fieldname, displayName, formatName);
    }
}
