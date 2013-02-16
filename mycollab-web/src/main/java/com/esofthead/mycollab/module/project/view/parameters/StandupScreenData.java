package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.StandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class StandupScreenData {
	public static class Search extends ScreenData<StandupReportSearchCriteria> {

		public Search(StandupReportSearchCriteria params) {
			super(params);
		}
	}

	public static class Add extends ScreenData<StandupReport> {

		public Add(StandupReport params) {
			super(params);
		}
	}

	public static class Edit extends ScreenData<StandupReport> {

		public Edit(StandupReport params) {
			super(params);
		}
	}

	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
}
