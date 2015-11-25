package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.TimeTrackingScreenData;
import com.esofthead.mycollab.module.project.view.time.ITimeTrackingContainer;
import com.esofthead.mycollab.module.project.view.time.ITimeTrackingPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author MyCollab Ltd
 * @since 2.0
 *
 */
public class TimeTrackingPresenter extends AbstractPresenter<ITimeTrackingContainer> implements ITimeTrackingPresenter {
	private static final long serialVersionUID = 1L;

	public TimeTrackingPresenter() {
		super(ITimeTrackingContainer.class);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView(ProjectTypeConstants.TIME);

		view.removeAllComponents();
		AbstractPresenter presenter;

		if (data instanceof TimeTrackingScreenData.Search) {
			presenter = PresenterResolver.getPresenter(TimeTrackingListPresenter.class);
		} else {
			throw new MyCollabException("No support screen data " + data);
		}

		presenter.go(view, data);
	}
}
