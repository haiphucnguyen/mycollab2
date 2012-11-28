package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.Presenter;

public interface MyFeedsView {
	interface MyFeedsPresenter extends Presenter {
		void doDefaultSearch();
	}
}
