package com.esofthead.mycollab.module.project.view.risk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.RiskScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class RiskPresenter extends AbstractPresenter<RiskContainer> {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(RiskPresenter.class);

	public RiskPresenter() {
		super(RiskContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
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
			log.debug("Go to projectMember add view");
			RiskAddPresenter presenter = PresenterResolver
					.getPresenter(RiskAddPresenter.class);
			presenter.go(view, data);
		} else if (data instanceof ScreenData.Preview) {
			log.debug("Go to projectMember preview view");
			RiskReadPresenter presenter = PresenterResolver
					.getPresenter(RiskReadPresenter.class);
			presenter.go(view, data);
		}
	}

	@Override
	public void handleChain(ComponentContainer container,
			PageActionChain pageActionChain) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Risks");

		view.removeAllComponents();

		ScreenData pageAction = pageActionChain.pop();
		if (pageAction instanceof RiskScreenData.Read) {
			RiskReadPresenter presenter = PresenterResolver
					.getPresenter(RiskReadPresenter.class);
			presenter.go(this.view, pageAction);
		}
	}

}
