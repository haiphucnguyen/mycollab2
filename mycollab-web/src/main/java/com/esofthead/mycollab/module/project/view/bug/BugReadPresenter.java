package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class BugReadPresenter extends AbstractPresenter<BugReadView> {
	private static final long serialVersionUID = 1L;

	public BugReadPresenter() {
		super(BugReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		BugContainer bugContainer = (BugContainer) container;
		bugContainer.removeAllComponents();
		bugContainer.addComponent(view.getWidget());

		if (data.getParams() instanceof Integer) {
			BugService bugService = AppContext
					.getSpringBean(BugService.class);
			SimpleBug bug = bugService.findBugById((Integer) data
					.getParams());
			view.previewItem(bug);
		}
	}

}
