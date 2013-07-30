package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.view.parameters.ComponentScreenData;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ComponentPresenter
		extends
		AbstractPresenter<com.esofthead.mycollab.module.project.view.bug.ComponentContainer> {
	private static final long serialVersionUID = 1L;

	public ComponentPresenter() {
		super(
				com.esofthead.mycollab.module.project.view.bug.ComponentContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(com.vaadin.ui.ComponentContainer container,
			ScreenData<?> data) {
		TrackerContainer trackerContainer = (TrackerContainer) container;
		trackerContainer.gotoSubView("Components");

		view.removeAllComponents();

		AbstractPresenter<?> presenter = null;

		if (data instanceof ComponentScreenData.Add) {
			presenter = PresenterResolver
					.getPresenter(ComponentAddPresenter.class);
		} else if (data instanceof ComponentScreenData.Edit) {
			presenter = PresenterResolver
					.getPresenter(ComponentAddPresenter.class);
		} else if (data instanceof ComponentScreenData.Search) {
			presenter = PresenterResolver
					.getPresenter(ComponentListPresenter.class);
		} else if (data instanceof ComponentScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(ComponentReadPresenter.class);
		} else if (data == null) {
			ComponentSearchCriteria criteria = new ComponentSearchCriteria();
			criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			data = new ComponentScreenData.Search(criteria);
			presenter = PresenterResolver
					.getPresenter(ComponentListPresenter.class);
		} else {
			throw new MyCollabException("Do not support screen data");
		}

		presenter.go(view, data);
	}

}
