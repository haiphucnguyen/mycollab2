package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.ProblemScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ProblemPresenter extends AbstractPresenter<ProblemContainer> {

	private static final long serialVersionUID = 1L;

	public ProblemPresenter() {
		super(ProblemContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Problems");

		view.removeAllComponents();

		AbstractPresenter presenter;

		if (data instanceof ProblemScreenData.Search) {
			presenter = PresenterResolver
					.getPresenter(ProblemListPresenter.class);
		} else if (data instanceof ScreenData.Add
				|| data instanceof ScreenData.Edit) {
			presenter = PresenterResolver
					.getPresenter(ProblemAddPresenter.class);
		} else if (data instanceof ProblemScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(ProblemReadPresenter.class);
		} else {
			throw new MyCollabException("Do not support screen data " + data);
		}

		presenter.go(view, data);
	}

	@Override
	public void handleChain(ComponentContainer container,
			PageActionChain pageActionChain) {
		ScreenData pageAction = pageActionChain.pop();
		onGo(container, pageAction);
	}

}
