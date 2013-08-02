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
public class ProjectMemberEvent {
    public static class Search extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public Search(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoList extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoList(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoAdd extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoAdd(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoRead extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoRead(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoEdit extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoEdit(Object source, Object data) {
            super(source, data);
        }
    }
}
