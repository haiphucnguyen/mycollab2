package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.StandupScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class StandupPresenter extends AbstractPresenter<StandupContainer> {
	private static final long serialVersionUID = 1L;

	public StandupPresenter() {
		super(StandupContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("StandUp");

		view.removeAllComponents();

		if (data instanceof StandupScreenData.Search) {
			StandupListPresenter presenter = PresenterResolver
					.getPresenter(StandupListPresenter.class);
			presenter.go(view, data);

		} else if (data instanceof StandupScreenData.Add
				|| data instanceof StandupScreenData.Edit) {
			StandupAddPresenter presenter = PresenterResolver
					.getPresenter(StandupAddPresenter.class);
			presenter.go(view, data);
		} else if (data instanceof StandupScreenData.Read) {
			StandupReadPresenter presenter = PresenterResolver
					.getPresenter(StandupReadPresenter.class);
			presenter.go(view, data);
		}
	}

}
