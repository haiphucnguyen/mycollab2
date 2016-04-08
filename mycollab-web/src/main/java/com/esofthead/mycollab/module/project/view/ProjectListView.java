package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.vaadin.web.ui.IListView;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public interface ProjectListView extends IListView<ProjectSearchCriteria, SimpleProject> {
    String VIEW_DEF_ID = "project-list";
}
