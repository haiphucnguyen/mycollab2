package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.view.parameters.VersionScreenData;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class VersionPresenter extends AbstractPresenter<VersionContainer> {
	private static final long serialVersionUID = 1L;

	public VersionPresenter() {
		super(VersionContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		TrackerContainer trackerContainer = (TrackerContainer) container;
		trackerContainer.gotoSubView("Versions");

		view.removeAllComponents();

		AbstractPresenter<?> presenter = null;

		if (data instanceof VersionScreenData.Add) {
			presenter = PresenterResolver
					.getPresenter(VersionAddPresenter.class);
		} else if (data instanceof VersionScreenData.Edit) {
			presenter = PresenterResolver
					.getPresenter(VersionAddPresenter.class);
		} else if (data instanceof VersionScreenData.Search) {
			presenter = PresenterResolver
					.getPresenter(VersionListPresenter.class);
		} else if (data instanceof VersionScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(VersionReadPresenter.class);
		} else if (data == null) {
			VersionSearchCriteria criteria = new VersionSearchCriteria();
			criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			data = new VersionScreenData.Search(criteria);
			presenter = PresenterResolver
					.getPresenter(VersionListPresenter.class);
		} else {
			throw new MyCollabException("Do not support screen data");
		}

		presenter.go(view, data);

	}

}
