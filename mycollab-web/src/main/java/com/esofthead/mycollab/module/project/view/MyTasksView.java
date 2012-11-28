package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface MyTasksView extends View {
	interface MyTasksPresenter extends Presenter {
		void doDefaultSearch();
	}
}
