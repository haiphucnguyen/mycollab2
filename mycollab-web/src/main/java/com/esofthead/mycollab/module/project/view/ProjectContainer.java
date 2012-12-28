package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectContainer extends AbstractView {

    private ProjectController controller;

    public ProjectContainer() {
        controller = new ProjectController(this);
    }

    public void gotoProjectPage() {
        UserDashboardView userDashboard = ViewManager
                .getView(UserDashboardView.class);
        this.removeAllComponents();
        this.addComponent(userDashboard);
        this.setComponentAlignment(userDashboard, Alignment.MIDDLE_CENTER);
    }

    private void gotoMyProjectList() {
        UserDashboardView userDashboard = ViewManager
                .getView(UserDashboardView.class);
        this.removeAllComponents();
        this.addComponent(userDashboard);
        userDashboard.gotoMyProjectList();
    }

    private void gotoMyProject() {
        ProjectView projectDashboard = ViewManager
                .getView(ProjectView.class);
        this.removeAllComponents();
        this.addComponent(projectDashboard);
    }
}
