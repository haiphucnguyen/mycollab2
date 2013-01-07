package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectDashboardViewImpl extends AbstractView implements ProjectDashboardView {

    public ProjectDashboardViewImpl() {
        this.setSpacing(true);
        this.setMargin(true);
    }

    @Override
    public void displayDashboard() {
        this.removeAllComponents();
        this.addComponent(new ProjectInformationView());
        
    }
}
