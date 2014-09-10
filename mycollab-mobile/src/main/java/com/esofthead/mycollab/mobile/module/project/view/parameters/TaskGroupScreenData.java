package com.esofthead.mycollab.mobile.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */
public class TaskGroupScreenData {

	public static class List extends ScreenData<Object> {
		public List() {
			super(null);
		}
	}

	public static class Read extends ScreenData<Integer> {
		public Read(Integer param) {
			super(param);
		}
	}

	public static class Edit extends ScreenData<SimpleTaskList> {

		public Edit(SimpleTaskList params) {
			super(params);
		}

	}
}
