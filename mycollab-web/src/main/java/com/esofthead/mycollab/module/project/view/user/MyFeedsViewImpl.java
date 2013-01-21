package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class MyFeedsViewImpl extends AbstractView implements MyFeedsView {

    private static final long serialVersionUID = 1L;
    
    private ActivityStreamComponent activityStreamComponent;
    
    public MyFeedsViewImpl() {
        activityStreamComponent = new ActivityStreamComponent();
        this.addComponent(activityStreamComponent);
    }
    
    @Override
    public void displayFeeds() {
        activityStreamComponent.showFeeds();
    }
}
