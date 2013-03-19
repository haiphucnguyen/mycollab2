package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class BugScreenData {
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
}
