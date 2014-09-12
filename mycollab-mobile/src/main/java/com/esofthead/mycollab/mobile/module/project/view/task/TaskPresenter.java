package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.mobile.module.project.ui.InsideProjectNavigationMenu;
import com.esofthead.mycollab.mobile.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.mobile.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.vaadin.mobilecomponent.MobileNavigationManager;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */
public class TaskPresenter extends AbstractPresenter<TaskContainer> {

	private static final long serialVersionUID = 7999611450505328038L;

	public TaskPresenter() {
		super(TaskContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		InsideProjectNavigationMenu projectModuleMenu = (InsideProjectNavigationMenu) ((MobileNavigationManager) UI
				.getCurrent().getContent()).getNavigationMenu();
		projectModuleMenu.selectButton(AppContext
				.getMessage(ProjectCommonI18nEnum.VIEW_TASK));
		AbstractPresenter<?> presenter = null;

		if (data instanceof TaskGroupScreenData.List || data == null) {
			presenter = PresenterResolver
					.getPresenter(TaskGroupListPresenter.class);
		} else if (data instanceof TaskScreenData.List) {
			presenter = PresenterResolver.getPresenter(TaskListPresenter.class);
		}

		presenter.go(container, data);
	}

}
