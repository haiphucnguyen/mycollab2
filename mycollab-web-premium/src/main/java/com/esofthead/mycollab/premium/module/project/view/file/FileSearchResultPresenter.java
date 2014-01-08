package com.esofthead.mycollab.premium.module.project.view.file;

import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.resource.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class FileSearchResultPresenter extends
		AbstractPresenter<FileSearchResultView> {

	private static final long serialVersionUID = 1L;

	public FileSearchResultPresenter() {
		super(FileSearchResultView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		FileContainer projectViewContainer = (FileContainer) container;
		projectViewContainer.removeAllComponents();
		projectViewContainer.addComponent(view);

		FileSearchCriteria params = (FileSearchCriteria) data.getParams();
		view.displaySearchResult(params);
	}

}
