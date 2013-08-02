package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
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
	
	public static class Add extends ScreenData<BugWithBLOBs> {

		public Add(BugWithBLOBs params) {
			super(params);
		}
	}
	
	public static class Edit extends ScreenData<BugWithBLOBs> {

		public Edit(BugWithBLOBs params) {
			super(params);
		}
	}
}
