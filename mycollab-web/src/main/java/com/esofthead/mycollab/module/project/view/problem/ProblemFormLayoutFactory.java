package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class ProblemFormLayoutFactory implements IFormLayoutFactory {
    
    private static final long serialVersionUID = 1L;
    private GridFormLayoutHelper informationLayout;
    private String title;
    
    public ProblemFormLayoutFactory(String title) {
        this.title = title;
    }
    
    @Override
    public Layout getLayout() {
        AddViewLayout accountAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/project/problem.png"));
        
        accountAddLayout.addTopControls(createTopPanel());
        
        VerticalLayout layout = new VerticalLayout();
        
        Label organizationHeader = new Label("Problem Information");
        organizationHeader.setStyleName("h2");
        layout.addComponent(organizationHeader);
        
        informationLayout = new GridFormLayoutHelper(2, 7);
        informationLayout.getLayout().setWidth("900px");
        layout.addComponent(informationLayout.getLayout());
        layout.setComponentAlignment(informationLayout.getLayout(),
                Alignment.BOTTOM_CENTER);
        
        accountAddLayout.addBottomControls(createBottomPanel());
        
        accountAddLayout.addBody(layout);
        
        return accountAddLayout;
    }
    
    @Override
    public void attachField(Object propertyId, Field field) {
        if (propertyId.equals("issuename")) {
            informationLayout.addComponent(field, "Name", 0, 0, 2, "100%");
        } else if (propertyId.equals("description")) {
            informationLayout.addComponent(field, "Description", 0, 1, 2,
                    "100%");
        } else if (propertyId.equals("raisedbyuser")) {
            informationLayout.addComponent(field, "Raised by", 0, 2);
        } else if (propertyId.equals("type")) {
            informationLayout.addComponent(field, "Related to", 1, 2);
        } else if (propertyId.equals("assigntouser")) {
            informationLayout.addComponent(field, "Assigned to", 0, 3);
        } else if (propertyId.equals("impact")) {
            informationLayout.addComponent(field, "Impact", 1, 3);
        } else if (propertyId.equals("datedue")) {
            informationLayout.addComponent(field, "Date due", 0, 4);
        } else if (propertyId.equals("priority")) {
            informationLayout.addComponent(field, "Priority", 1, 4);
        } else if (propertyId.equals("status")) {
            informationLayout.addComponent(field, "Status", 0, 5);
        } else if (propertyId.equals("level")) {
            informationLayout.addComponent(field, "Rating", 1, 5);
        } else if (propertyId.equals("resolution")) {
            informationLayout.addComponent(field, "Resolution", 0, 6, 2, "100%");
        }
    }
    
    protected abstract Layout createTopPanel();
    
    protected abstract Layout createBottomPanel();
}
