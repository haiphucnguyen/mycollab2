package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.mobile.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

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
		AbstractPresenter<?> presenter = null;

		if (data instanceof TaskGroupScreenData.List || data == null) {
			presenter = PresenterResolver
					.getPresenter(TaskGroupListPresenter.class);
		}

		presenter.go(view, data);
	}

}
