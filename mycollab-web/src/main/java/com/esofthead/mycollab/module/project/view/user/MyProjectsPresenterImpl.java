package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.user.MyProjectsView.MyProjectPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class MyProjectsPresenterImpl extends AbstractPresenter implements MyProjectPresenter {

	private MyProjectsView view;
	private ProjectService projectService;
	private ProjectSearchCriteria searchCriteria;

	public MyProjectsPresenterImpl(MyProjectsView view) {
		this.view = view;
		projectService = AppContext.getSpringBean(ProjectService.class);
		bind();
	}

	private void bind() {
	}

	@Override
	public void doDefaultSearch() {
		ProjectSearchCriteria criteria = new ProjectSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		doSearch(criteria);
	}

	@Override
	public void doSearch(ProjectSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();

	}
	
	private void checkWhetherEnableTableActionControl() {
		
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		
	}
}
