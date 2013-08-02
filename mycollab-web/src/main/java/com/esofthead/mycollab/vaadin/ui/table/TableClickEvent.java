/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui.table;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

/**
 *
 * @author haiphucnguyen
 */
public class TableClickEvent extends ApplicationEvent {

    private String fieldName;

    public TableClickEvent(IBeanTable source, Object data, String fieldName) {
        super(source, data);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
