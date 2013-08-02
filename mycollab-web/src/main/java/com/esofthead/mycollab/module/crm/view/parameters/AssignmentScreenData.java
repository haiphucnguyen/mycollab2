package com.esofthead.mycollab.module.crm.view.parameters;

import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class AssignmentScreenData {
	public static class Add extends ScreenData<Task> {

		public Add(Task task) {
			super(task);
		}
	}

	public static class Edit extends ScreenData<Task> {

		public Edit(Task task) {
			super(task);
		}
	}
	
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
}
