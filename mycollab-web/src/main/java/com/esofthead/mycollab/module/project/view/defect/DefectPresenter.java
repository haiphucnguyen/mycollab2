package com.esofthead.mycollab.module.project.view.defect;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.risk.RiskAddPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskContainer;
import com.esofthead.mycollab.module.project.view.risk.RiskListPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskReadPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class DefectPresenter  extends AbstractPresenter<DefectContainer> {
	private static final long serialVersionUID = 1L;

	public DefectPresenter() {
		super(DefectContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Defects");

		view.removeAllComponents();

//		if (data instanceof ScreenData.Search) {
//			DefectListPresenter presenter = PresenterResolver
//					.getPresenter(DefectListPresenter.class);
//			presenter.go(view, data);
//
//		} else if (data instanceof ScreenData.Add
//				|| data instanceof ScreenData.Edit) {
//			DefectAddPresenter presenter = PresenterResolver
//					.getPresenter(DefectAddPresenter.class);
//			presenter.go(view, data);
//		} else if (data instanceof ScreenData.Preview) {
//			DefectReadPresenter presenter = PresenterResolver
//					.getPresenter(DefectReadPresenter.class);
//			presenter.go(view, data);
//		}
	}

}
