package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class RiskScreenData {
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
	
	public static class Add extends ScreenData<Risk> {

		public Add(Risk params) {
			super(params);
		}
	}
	
	public static class Edit extends ScreenData<Risk> {

		public Edit(Risk params) {
			super(params);
		}
	}

	public static class Search extends ScreenData<RiskSearchCriteria> {

		public Search(RiskSearchCriteria criteria) {
			super(criteria);
		}
	}
}
