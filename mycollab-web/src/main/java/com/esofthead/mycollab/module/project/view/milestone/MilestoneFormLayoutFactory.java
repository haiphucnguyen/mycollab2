/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public abstract class MilestoneFormLayoutFactory implements IFormLayoutFactory{
    private static final long serialVersionUID = 1L;
    private GridFormLayoutHelper informationLayout;
    private String title;
    
    public MilestoneFormLayoutFactory(String title) {
        this.title = title;
    }
    
    @Override
    public Layout getLayout() {
        AddViewLayout milestoneAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/project/milestone.png"));
        
        milestoneAddLayout.addTopControls(createTopPanel());
        
        VerticalLayout layout = new VerticalLayout();
        
        Label organizationHeader = new Label("Milestone Information");
        organizationHeader.setStyleName("h2");
        layout.addComponent(organizationHeader);
        
        informationLayout = new GridFormLayoutHelper(2, 3);
        informationLayout.getLayout().setWidth("100%");
        layout.addComponent(informationLayout.getLayout());
        layout.setComponentAlignment(informationLayout.getLayout(),
                Alignment.BOTTOM_CENTER);
        
        milestoneAddLayout.addBottomControls(createBottomPanel());
        
        milestoneAddLayout.addBody(layout);
        
        return milestoneAddLayout;
    }
    
    @Override
    public void attachField(Object propertyId, Field field) {
        if (propertyId.equals("name")) {
            informationLayout.addComponent(field, "Name", 0, 0, 2, "100%");
        } else if (propertyId.equals("startdate")) {
            informationLayout.addComponent(field, "Start Date", 0, 1);
        } else if (propertyId.equals("enddate")) {
            informationLayout.addComponent(field, "End Date", 0, 2);
        } else if (propertyId.equals("owner")) {
            informationLayout.addComponent(field, "Responsible User", 1, 1);
        } else if (propertyId.equals("flag")) {
            informationLayout.addComponent(field, "Flag", 1, 2);
        }
    }
    
    protected abstract Layout createTopPanel();
    
    protected abstract Layout createBottomPanel();
}
