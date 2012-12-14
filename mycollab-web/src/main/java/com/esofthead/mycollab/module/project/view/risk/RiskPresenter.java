package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

public class RiskPresenter extends AbstractPresenter {
	private static final long serialVersionUID = 1L;

	private RiskContainer view;

	public RiskPresenter(RiskContainer view) {
		this.view = view;
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Risks");

		view.removeAllComponents();

		if (data instanceof ScreenData.Search) {
			RiskListViewImpl listView = ViewManager
					.getView(RiskListViewImpl.class);
			new RiskListPresenter(listView).go(view, data);

		} else if (data instanceof ScreenData.Add
				|| data instanceof ScreenData.Edit) {
			RiskAddViewImpl addView = ViewManager
					.getView(RiskAddViewImpl.class);
			new RiskAddPresenter(addView).go(view, data);
		} else if (data instanceof ScreenData.Preview) {
			RiskReadViewImpl readView = ViewManager
					.getView(RiskReadViewImpl.class);
			new RiskReadPresenter(readView).go(view, data);
		}
	}

}
