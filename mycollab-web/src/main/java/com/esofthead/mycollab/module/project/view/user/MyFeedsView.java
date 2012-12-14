package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.vaadin.mvp.Presenter;

public interface MyFeedsView {
	interface MyFeedsPresenter extends Presenter {
		void doDefaultSearch();
	}
}
