package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class MilestoneScreenData {
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}

	public static class Search extends ScreenData<MilestoneSearchCriteria> {

		public Search(MilestoneSearchCriteria params) {
			super(params);
		}
	}
}
