package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.web.ui.DefaultPagedGrid;

import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
@ViewComponent
public class ProjectListViewImpl extends AbstractPageView implements ProjectListView {
    private ProjectSearchPanel projectSearchPanel;
    private DefaultPagedGrid<ProjectService, ProjectSearchCriteria, SimpleProject> projectGrid;

    public ProjectListViewImpl() {
        withMargin(true);
        projectSearchPanel = new ProjectSearchPanel();
        with(projectSearchPanel);
        projectGrid = new DefaultPagedGrid<>(ApplicationContextUtil.getSpringBean(ProjectService.class),
                SimpleProject.class, Arrays.asList(ProjectTableFieldDef.projectname()));
        with(projectGrid);
    }

    @Override
    public void display() {

    }


}
