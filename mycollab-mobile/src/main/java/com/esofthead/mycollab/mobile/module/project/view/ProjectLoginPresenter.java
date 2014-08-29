package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class ProjectLoginPresenter extends AbstractPresenter<ProjectLoginView> {

	private static final long serialVersionUID = -750325026975907368L;

	public ProjectLoginPresenter() {
		super(ProjectLoginView.class);
	}

	@Override
	protected void onGo(ComponentContainer navigationManager, ScreenData<?> data) {
		((NavigationManager) navigationManager).navigateTo(view.getWidget());
	}

}
