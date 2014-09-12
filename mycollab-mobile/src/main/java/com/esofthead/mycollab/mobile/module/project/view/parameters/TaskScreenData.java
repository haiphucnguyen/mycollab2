package com.esofthead.mycollab.mobile.module.project.view.parameters;

import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */
public class TaskScreenData {
	public static class List extends ScreenData<Integer> {
		public List(Integer param) {
			super(param);
		}
	}

	public static class Add extends ScreenData<Integer> {
		public Add() {
			super(null);
		}
	}

	public static class Read extends ScreenData<Integer> {
		public Read(Integer param) {
			super(param);
		}
	}

	public static class Edit extends ScreenData<SimpleTask> {
		public Edit(SimpleTask param) {
			super(param);
		}
	}
}
