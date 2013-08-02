package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class ComponentScreenData {
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}

	public static class Add extends ScreenData<Component> {

		public Add(Component component) {
			super(component);
		}
	}

	public static class Edit extends ScreenData<Component> {

		public Edit(Component component) {
			super(component);
		}
	}

	public static class Search extends ScreenData<ComponentSearchCriteria> {

		public Search(ComponentSearchCriteria criteria) {
			super(criteria);
		}
	}
}
