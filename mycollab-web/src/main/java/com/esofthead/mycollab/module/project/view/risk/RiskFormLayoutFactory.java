package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class RiskFormLayoutFactory implements IFormLayoutFactory {
    
    private static final long serialVersionUID = 1L;
    private GridFormLayoutHelper informationLayout;
    private String title;
    
    public RiskFormLayoutFactory(String title) {
        this.title = title;
    }
    
    @Override
    public Layout getLayout() {
        AddViewLayout riskAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/project/projectMember.png"));
        
        riskAddLayout.addTopControls(createTopPanel());
        
        VerticalLayout layout = new VerticalLayout();
        
        Label organizationHeader = new Label("Risk Information");
        organizationHeader.setStyleName("h2");
        layout.addComponent(organizationHeader);
        
        informationLayout = new GridFormLayoutHelper(2, 7);
        informationLayout.getLayout().setWidth("100%");
        layout.addComponent(informationLayout.getLayout());
        layout.setComponentAlignment(informationLayout.getLayout(),
                Alignment.BOTTOM_CENTER);
        
        riskAddLayout.addBottomControls(createBottomPanel());
        
        riskAddLayout.addBody(layout);
        
        return riskAddLayout;
    }
    
    @Override
    public void attachField(Object propertyId, Field field) {
        if (propertyId.equals("riskname")) {
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
        } else if (propertyId.equals("consequence")) {
            informationLayout.addComponent(field, "Consequence", 1, 3);
        } else if (propertyId.equals("datedue")) {
            informationLayout.addComponent(field, "Date due", 0, 4);
        } else if (propertyId.equals("probalitity")) {
            informationLayout.addComponent(field, "Probality", 1, 4);
        } else if (propertyId.equals("status")) {
            informationLayout.addComponent(field, "Status", 0, 5);
        } else if (propertyId.equals("level")) {
            informationLayout.addComponent(field, "Rating", 1, 5);
        } else if (propertyId.equals("response")) {
            informationLayout.addComponent(field, "Response", 0, 6, 2, "100%");
        }
    }
    
    protected abstract Layout createTopPanel();
    
    protected abstract Layout createBottomPanel();
}
