package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class MessageScreenData {
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}

	public static class Search extends ScreenData<MessageSearchCriteria> {

		public Search(MessageSearchCriteria params) {
			super(params);
		}
	}
}
