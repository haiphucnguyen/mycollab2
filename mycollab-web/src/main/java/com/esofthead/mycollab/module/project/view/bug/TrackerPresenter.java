package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.billing.BillingPlanChecker;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ComponentScreenData;
import com.esofthead.mycollab.module.project.view.parameters.VersionScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class TrackerPresenter extends AbstractPresenter<TrackerContainer> {

	private static final long serialVersionUID = 1L;

	public TrackerPresenter() {
		super(TrackerContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Bugs");

		view.removeAllComponents();

		Presenter presenter = null;

		if (BillingPlanChecker.isBugComponentEnable()) {
			if (data instanceof BugScreenData.Search) {
				presenter = PresenterResolver
						.getPresenter(BugListPresenter.class);
			} else if (data instanceof BugScreenData.Add
					|| data instanceof BugScreenData.Edit) {
				presenter = PresenterResolver
						.getPresenter(BugAddPresenter.class);
			} else if (data instanceof BugScreenData.Read) {
				presenter = PresenterResolver
						.getPresenter(BugReadPresenter.class);
			} else if (data instanceof ComponentScreenData.Add) {
				presenter = PresenterResolver
						.getPresenter(ComponentAddPresenter.class);
			} else if (data instanceof ComponentScreenData.Edit) {
				presenter = PresenterResolver
						.getPresenter(ComponentAddPresenter.class);
			} else if (data instanceof ComponentScreenData.Search) {
				presenter = PresenterResolver
						.getPresenter(ComponentListPresenter.class);
			} else if (data instanceof ComponentScreenData.Read) {
				presenter = PresenterResolver
						.getPresenter(ComponentReadPresenter.class);
			} else if (data instanceof VersionScreenData.Add) {
				presenter = PresenterResolver
						.getPresenter(VersionAddPresenter.class);
			} else if (data instanceof VersionScreenData.Edit) {
				presenter = PresenterResolver
						.getPresenter(VersionAddPresenter.class);
			} else if (data instanceof VersionScreenData.Search) {
				presenter = PresenterResolver
						.getPresenter(VersionListPresenter.class);
			} else if (data instanceof VersionScreenData.Read) {
				presenter = PresenterResolver
						.getPresenter(VersionReadPresenter.class);
			} else if (data == null
					|| data instanceof BugScreenData.GotoDashboard) {
				presenter = PresenterResolver
						.getPresenter(BugDashboardPresenter.class);
			}
		} else {
			presenter = PresenterResolver
					.getPresenter(BugAdvertisementPresenter.class);
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
