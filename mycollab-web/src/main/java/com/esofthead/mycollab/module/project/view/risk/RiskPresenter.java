package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class RiskPresenter extends AbstractPresenter<RiskContainer> {
	private static final long serialVersionUID = 1L;

	public RiskPresenter() {
		super(RiskContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Risks");

		view.removeAllComponents();

		if (data instanceof ScreenData.Search) {
			RiskListPresenter presenter = PresenterResolver
					.getPresenter(RiskListPresenter.class);
			presenter.go(view, data);

		} else if (data instanceof ScreenData.Add
				|| data instanceof ScreenData.Edit) {
			RiskAddPresenter presenter = PresenterResolver
					.getPresenter(RiskAddPresenter.class);
			presenter.go(view, data);
		} else if (data instanceof ScreenData.Preview) {
			RiskReadPresenter presenter = PresenterResolver
					.getPresenter(RiskReadPresenter.class);
			presenter.go(view, data);
		}
	}

}
