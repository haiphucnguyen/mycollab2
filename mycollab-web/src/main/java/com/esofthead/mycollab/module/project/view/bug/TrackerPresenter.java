package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ComponentScreenData;
import com.esofthead.mycollab.module.project.view.parameters.VersionScreenData;
import com.esofthead.mycollab.shell.BillingPlanChecker;
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

		Presenter presenter = null;

		if (BillingPlanChecker.isBugComponentEnable()) {
			if (ClassUtils.instanceOf(data, BugScreenData.Search.class,
					BugScreenData.Add.class, BugScreenData.Edit.class,
					BugScreenData.Read.class)) {
				presenter = PresenterResolver.getPresenter(BugPresenter.class);
			} else if (ClassUtils.instanceOf(data,
					ComponentScreenData.Add.class,
					ComponentScreenData.Edit.class,
					ComponentScreenData.Search.class,
					ComponentScreenData.Read.class)) {
				presenter = PresenterResolver
						.getPresenter(ComponentPresenter.class);
			} else if (ClassUtils.instanceOf(data, VersionScreenData.Add.class,
					VersionScreenData.Edit.class,
					VersionScreenData.Search.class,
					VersionScreenData.Read.class)) {
				presenter = PresenterResolver
						.getPresenter(VersionPresenter.class);
			} else if (data == null
					|| data instanceof BugScreenData.GotoDashboard) {
				presenter = PresenterResolver
						.getPresenter(BugDashboardPresenter.class);
			} else {
				throw new MyCollabException("Do not support screen data "
						+ data);
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
