package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class ProblemScreenData {
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
	
	public static class Add extends ScreenData<Problem> {

		public Add(Problem params) {
			super(params);
		}
	}
	
	public static class Edit extends ScreenData<Problem> {

		public Edit(Problem params) {
			super(params);
		}
	}

	public static class Search extends ScreenData<ProblemSearchCriteria> {

		public Search(ProblemSearchCriteria criteria) {
			super(criteria);
		}
	}
}
