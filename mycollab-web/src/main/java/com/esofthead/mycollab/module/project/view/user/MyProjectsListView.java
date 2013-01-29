package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;

public interface MyProjectsListView extends View {

    IPagedBeanTable<ProjectSearchCriteria, SimpleProject> getPagedBeanTable();
}
