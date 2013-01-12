package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectDashboardViewImpl extends AbstractView implements ProjectDashboardView {

    private ActivityStreamComponent activityPanel;
    private ProjectInformationComponent prjView;
    
    public ProjectDashboardViewImpl() {
        this.setSpacing(true);
        this.setMargin(true);
        
        prjView = new ProjectInformationComponent();
        activityPanel = new ActivityStreamComponent();
        
        this.addComponent(activityPanel);
        this.addComponent(prjView);
    }

    @Override
    public void displayDashboard() {
        activityPanel.showProjectFeeds();
        prjView.displayProjectInformation();
    }
}
