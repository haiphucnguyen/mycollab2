package com.esofthead.mycollab.module.project.view.risk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
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
		AbstractPresenter presenter = null;

		if (data instanceof RiskScreenData.Search) {
			presenter = PresenterResolver.getPresenter(RiskListPresenter.class);
		} else if (data instanceof RiskScreenData.Add
				|| data instanceof RiskScreenData.Edit) {
			log.debug("Go to projectMember add view");
			presenter = PresenterResolver.getPresenter(RiskAddPresenter.class);
		} else if (data instanceof RiskScreenData.Read) {
			log.debug("Go to projectMember preview view");
			presenter = PresenterResolver.getPresenter(RiskReadPresenter.class);
		} else {
			throw new MyCollabException("No support screen data " + data);
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
