package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class MyFeedsViewImpl extends AbstractView implements MyFeedsView {

    private static final long serialVersionUID = 1L;
    
    private ActivityStreamComponent activityStreamComponent;
    
    public MyFeedsViewImpl() {
        this.addComponent(new Label("Feeds"));
        activityStreamComponent = new ActivityStreamComponent();
        this.addComponent(activityStreamComponent);
    }
    
    @Override
    public void displayFeeds() {
        System.out.println("Display feeds");
        activityStreamComponent.showFeeds();
    }
}
