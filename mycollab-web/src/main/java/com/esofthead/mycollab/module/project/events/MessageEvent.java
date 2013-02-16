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
public class MessageEvent {

    public static class GotoList extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoList(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoRead extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoRead(Object source, Object data) {
            super(source, data);
        }
    }
}
