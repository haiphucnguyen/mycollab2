package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class MyDefectsPresenter extends AbstractPresenter<MyDefectsView>
		implements ListPresenter<BugSearchCriteria> {

	private static final long serialVersionUID = 1L;

	public MyDefectsPresenter() {
		super(MyDefectsView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSearch(BugSearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		
	}

}
