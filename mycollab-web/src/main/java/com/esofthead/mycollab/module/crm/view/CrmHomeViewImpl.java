package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class CrmHomeViewImpl extends AbstractView implements CrmHomeView {
    
    public CrmHomeViewImpl() {
        this.setSpacing(true);
        this.setMargin(true);
    }
    
    @Override
    public void displayDashboard() {
        this.removeAllComponents();
        
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        
        VerticalLayout myAssignmentsLayout = new VerticalLayout();
        
        layout.addComponent(myAssignmentsLayout);
        
        VerticalLayout streamsLayout = new VerticalLayout();
        streamsLayout.addComponent(new ActivityStreamPanel());
        layout.addComponent(streamsLayout);
        
        this.addComponent(layout);
    }
}
