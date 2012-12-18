package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ProblemReadPresenter extends AbstractPresenter<ProblemReadView> {
	private static final long serialVersionUID = 1L;

	public ProblemReadPresenter() {
		super(ProblemReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProblemContainer riskContainer = (ProblemContainer) container;
		riskContainer.addComponent(view.getWidget());

		if (data.getParams() instanceof Integer) {
			ProblemService problemService = AppContext
					.getSpringBean(ProblemService.class);
			SimpleProblem risk = problemService.findProblemById((Integer) data
					.getParams());
			view.previewItem(risk);

		}
	}

}
