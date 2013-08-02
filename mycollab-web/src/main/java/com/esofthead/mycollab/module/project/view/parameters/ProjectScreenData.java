package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class ProjectScreenData {
	public static class Goto extends ScreenData<Integer> {

		public Goto(Integer params) {
			super(params);
		}
	}

	public static class Add extends ScreenData<Project> {

		public Add(Project params) {
			super(params);
		}
	}

	public static class Edit extends ScreenData<Project> {

		public Edit(Project params) {
			super(params);
		}
	}
}
