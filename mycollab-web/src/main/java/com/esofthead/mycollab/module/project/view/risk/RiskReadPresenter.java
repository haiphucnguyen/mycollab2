package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class RiskReadPresenter extends AbstractPresenter<RiskReadView> {
	private static final long serialVersionUID = 1L;

	public RiskReadPresenter() {
		super(RiskReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		RiskContainer riskContainer = (RiskContainer) container;
		riskContainer.addComponent(view.getWidget());

		if (data.getParams() instanceof Integer) {
			RiskService riskService = AppContext
					.getSpringBean(RiskService.class);
			SimpleRisk risk = riskService.findRiskById((Integer) data
					.getParams());
			view.previewItem(risk);

		}
	}

}
