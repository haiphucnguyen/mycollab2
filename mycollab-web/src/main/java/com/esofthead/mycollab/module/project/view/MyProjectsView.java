package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;

public interface MyProjectsView extends View {
	
	IPagedBeanTable<ProjectService, ProjectSearchCriteria, SimpleProject> getPagedBeanTable();

	interface MyProjectPresenter extends Presenter {

		void doDefaultSearch();

		void doSearch(ProjectSearchCriteria searchCriteria);
	}
}
