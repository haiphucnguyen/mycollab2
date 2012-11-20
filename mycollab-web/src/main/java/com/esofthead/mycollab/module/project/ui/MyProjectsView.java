package com.esofthead.mycollab.module.project.ui;

import java.util.List;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.TemplateSearchableView;

public interface MyProjectsView extends
		TemplateSearchableView<ProjectSearchCriteria> {

	void displayProjects(List<SimpleProject> projects);
}
