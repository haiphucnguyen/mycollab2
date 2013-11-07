package com.esofthead.mycollab.community.module.project.view.risk;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.risk.IRiskContainer;
import com.esofthead.mycollab.module.project.view.risk.IRiskPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class RiskPresenter extends AbstractPresenter<IRiskContainer> implements
		IRiskPresenter {
	private static final long serialVersionUID = 1L;

	public RiskPresenter() {
		super(IRiskContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Risks");

	}

}
