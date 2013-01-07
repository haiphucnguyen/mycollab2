package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

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
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setWidth("100%");
        
        VerticalLayout myAssignmentsLayout = new VerticalLayout();
        
        layout.addComponent(myAssignmentsLayout);
        
        VerticalLayout streamsLayout = new VerticalLayout();
        streamsLayout.setWidth("400px");
        streamsLayout.addComponent(new ActivityStreamPanel());
        layout.addComponent(streamsLayout);
        layout.setComponentAlignment(streamsLayout, Alignment.MIDDLE_RIGHT);
        
        layout.setExpandRatio(myAssignmentsLayout, 1.0f);
        
        this.addComponent(layout);
    }
}
