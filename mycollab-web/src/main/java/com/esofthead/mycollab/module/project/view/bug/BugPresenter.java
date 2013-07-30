package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class BugPresenter extends AbstractPresenter<BugContainer> {
	private static final long serialVersionUID = 1L;

	public BugPresenter() {
		super(BugContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		TrackerContainer trackerContainer = (TrackerContainer) container;
		trackerContainer.gotoSubView("Bugs");

		view.removeAllComponents();

		AbstractPresenter<?> presenter = null;

		if (data instanceof BugScreenData.Search) {
			presenter = PresenterResolver.getPresenter(BugListPresenter.class);
		} else if (data instanceof BugScreenData.Add
				|| data instanceof BugScreenData.Edit) {
			presenter = PresenterResolver.getPresenter(BugAddPresenter.class);
		} else if (data instanceof BugScreenData.Read) {
			presenter = PresenterResolver.getPresenter(BugReadPresenter.class);
		} else if (data == null) {
			BugSearchCriteria criteria = new BugSearchCriteria();
			criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			data = new BugScreenData.Search(new BugSearchParameter("All Bugs",
					criteria));

		} else {
			throw new MyCollabException("Do not support screen data");
		}

		presenter.go(view, data);
	}

}
