package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.view.ProjectInformationComponent;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class ProjectDashboardViewImpl extends AbstractView implements ProjectDashboardView {

    private ActivityStreamComponent activityPanel;
    private ProjectInformationComponent prjView;
    private ProjectMembersWidget membersWidget;
    private ProjectHighlightWidget highlightWidget;
    
    public ProjectDashboardViewImpl() {
        this.setSpacing(true);
        this.setMargin(true);
        
        prjView = new ProjectInformationComponent();
        this.addComponent(prjView);
        
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);
        this.addComponent(layout);
        
        activityPanel = new ActivityStreamComponent();
        layout.addComponent(activityPanel);
        
        VerticalLayout rightPanel = new VerticalLayout();
        rightPanel.setSpacing(true);
        layout.addComponent(rightPanel);
        
        membersWidget = new ProjectMembersWidget();
        highlightWidget = new ProjectHighlightWidget();
        rightPanel.addComponent(membersWidget);
        rightPanel.addComponent(highlightWidget);
    }

    @Override
    public void displayDashboard() {
        activityPanel.showProjectFeeds();
        prjView.displayProjectInformation();
        membersWidget.showInformation();
        highlightWidget.showInformation();
    }
}
