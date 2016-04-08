package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class ProjectListPresenter extends AbstractPresenter<ProjectListView> {
    public ProjectListPresenter() {
        super(ProjectListView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        view.display();
    }
}
