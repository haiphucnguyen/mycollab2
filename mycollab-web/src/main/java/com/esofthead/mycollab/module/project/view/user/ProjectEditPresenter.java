/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.view.ProjectContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectEditPresenter extends AbstractPresenter<ProjectEditView> {

    public ProjectEditPresenter() {
        super(ProjectEditView.class);
    }
    
    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectContainer projectViewContainer = (ProjectContainer) container;
        projectViewContainer.removeAllComponents();
        projectViewContainer.addComponent(view.getWidget());
        view.editItem((Project) data.getParams());
    }
}
