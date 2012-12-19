package com.esofthead.mycollab.module.project.view.defect;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.view.risk.RiskContainer;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class BugReadPresenter extends AbstractPresenter<BugReadView> {
	private static final long serialVersionUID = 1L;

	public BugReadPresenter() {
		super(BugReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		RiskContainer riskContainer = (RiskContainer) container;
		riskContainer.addComponent(view.getWidget());

		if (data.getParams() instanceof Integer) {
//			BugService riskService = AppContext
//					.getSpringBean(BugService.class);
//			SimpleRisk risk = riskService.f((Integer) data
//					.getParams());
//			view.previewItem(risk);

		}
	}

}
