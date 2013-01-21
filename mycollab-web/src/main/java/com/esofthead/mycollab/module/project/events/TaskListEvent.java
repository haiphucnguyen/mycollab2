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
    
    public static class GotoEdit extends ApplicationEvent {

        public GotoEdit(Object source, Object data) {
            super(source, data);
        }
    }
    
    public static class GotoAdd extends ApplicationEvent {

        public GotoAdd(Object source, Object data) {
            super(source, data);
        }
    }
    
    public static class ReoderTaskList extends ApplicationEvent {

        public ReoderTaskList(Object source, Object data) {
            super(source, data);
        }
    }
    
    public static class GotoTaskListScreen extends ApplicationEvent {

        public GotoTaskListScreen(Object source, Object data) {
            super(source, data);
        }
    }
}
