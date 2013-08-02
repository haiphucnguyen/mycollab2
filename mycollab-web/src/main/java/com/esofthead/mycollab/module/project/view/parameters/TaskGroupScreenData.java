package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class TaskGroupScreenData {
	public static class GotoDashboard extends ScreenData {
		public GotoDashboard() {
			super(null);
		}
	}

	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}

	public static class ReorderTaskListRequest extends ScreenData {

		public ReorderTaskListRequest() {
			super(null);
		}
	}

	public static class DisplayGanttChartRequest extends ScreenData {
		private TaskListSearchCriteria searchCriteria;

		public DisplayGanttChartRequest(TaskSearchCriteria searchCriteria) {
			super(searchCriteria);
		}
	}

	public static class Edit extends ScreenData<TaskList> {

		public Edit(TaskList taskList) {
			super(taskList);
		}
	}

	public static class Add extends ScreenData<TaskList> {

		public Add(TaskList taskList) {
			super(taskList);
		}
	}
}
