package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class FileScreenData {

	public static class GotoDashboard extends ScreenData {
		public GotoDashboard() {
			super(null);
		}
	}

	public static class Search extends ScreenData<String[]> {

		public Search(String[] criteria) {
			super(criteria);
		}
	}
}
