/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

/**
 *
 * @author haiphucnguyen
 */
public class TaskListEvent {

    public static class GotoRead extends ApplicationEvent {

        public GotoRead(Object source, Object data) {
            super(source, data);
        }
    }
}
