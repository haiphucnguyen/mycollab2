package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class RiskAddPresenter extends AbstractPresenter {
	private static final long serialVersionUID = 1L;

	private RiskAddView view;

	public RiskAddPresenter(RiskAddView view) {
		this.view = view;
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		RiskContainer riskContainer = (RiskContainer) container;
		riskContainer.addComponent(view.getWidget());
		view.editItem((Risk) data.getParams());
	}

}
