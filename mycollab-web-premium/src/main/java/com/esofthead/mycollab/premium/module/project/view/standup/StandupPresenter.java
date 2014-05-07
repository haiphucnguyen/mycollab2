package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.StandupScreenData;
import com.esofthead.mycollab.module.project.view.standup.IStandupContainer;
import com.esofthead.mycollab.module.project.view.standup.IStandupPresenter;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class StandupPresenter extends AbstractPresenter<IStandupContainer>
		implements IStandupPresenter {
	private static final long serialVersionUID = 1L;

	public StandupPresenter() {
		super(IStandupContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("StandUp");

		view.removeAllComponents();

		AbstractPresenter presenter;

		if (AppContext.isStandupComponentEnable()) {
			if (data instanceof StandupScreenData.Search) {
				presenter = PresenterResolver
						.getPresenter(StandupListPresenter.class);
			} else if (data instanceof StandupScreenData.Add
					|| data instanceof StandupScreenData.Edit) {
				presenter = PresenterResolver
						.getPresenter(StandupAddPresenter.class);
			} else if (data instanceof StandupScreenData.Read) {
				presenter = PresenterResolver
						.getPresenter(StandupReadPresenter.class);
			} else {
				throw new MyCollabException("Do not support screen data: "
						+ data);
			}
		} else {
			presenter = PresenterResolver
					.getPresenter(StandupAdvertisementPresenter.class);
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
