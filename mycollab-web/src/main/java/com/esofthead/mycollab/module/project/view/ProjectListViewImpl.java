package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
@ViewComponent
public class ProjectListViewImpl extends AbstractPageView implements ProjectListView {
    private ProjectSearchPanel projectSearchPanel;

    public ProjectListViewImpl() {
        withMargin(true);
        projectSearchPanel = new ProjectSearchPanel();
        with(projectSearchPanel);
    }

    @Override
    public void display() {


    }
}
