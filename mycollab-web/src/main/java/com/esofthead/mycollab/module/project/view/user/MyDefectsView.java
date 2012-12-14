package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface MyDefectsView extends View {
	interface MyDefectsPresenter extends Presenter {
		void doDefaultSearch();
	}
}
