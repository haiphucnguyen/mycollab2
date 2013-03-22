package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class BugScreenData {
	public static class GotoDashboard extends ScreenData {

		public GotoDashboard() {
			super(null);
		}
	}

	public static class Search extends ScreenData<BugSearchParameter> {

		public Search(BugSearchParameter params) {
			super(params);
		}
	}

	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
	
	public static class Add extends ScreenData<Bug> {

		public Add(Bug params) {
			super(params);
		}
	}
	
	public static class Edit extends ScreenData<Bug> {

		public Edit(Bug params) {
			super(params);
		}
	}
}
