package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.HorizontalLayout;

@ViewComponent
public class MyFeedsViewImpl extends AbstractView implements MyFeedsView {

    private static final long serialVersionUID = 1L;
    
    private ActivityStreamComponent activityStreamComponent;
    
    private TaskStatusComponent taskStatusComponent;
    
    public MyFeedsViewImpl() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);
        
        activityStreamComponent = new ActivityStreamComponent();
        taskStatusComponent = new TaskStatusComponent();
        layout.addComponent(activityStreamComponent);
        layout.addComponent(taskStatusComponent);
        
        this.addComponent(layout);
    }
    
    @Override
    public void displayFeeds() {
        activityStreamComponent.showFeeds();
        taskStatusComponent.showProjectTasksByStatus();
    }
}
