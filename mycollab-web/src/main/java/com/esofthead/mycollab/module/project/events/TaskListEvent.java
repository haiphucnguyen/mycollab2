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

	public static class GotoAdd extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoAdd(Object source, Object data) {
			super(source, data);
		}
	}

	public static class ReoderTaskList extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public ReoderTaskList(Object source, Object data) {
			super(source, data);
		}
	}

	public static class SaveReoderTaskList extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public SaveReoderTaskList(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoGanttChartView extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoGanttChartView(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoTaskListScreen extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoTaskListScreen(Object source, Object data) {
			super(source, data);
		}
	}
}
