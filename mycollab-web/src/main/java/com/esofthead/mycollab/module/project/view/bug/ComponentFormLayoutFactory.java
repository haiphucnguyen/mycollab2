/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.view.bug.VersionFormLayoutFactory.VersionInformationLayout;
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
public abstract class ComponentFormLayoutFactory implements IFormLayoutFactory {

    private String title;
    private ComponentInformationLayout informationLayout;

    public ComponentFormLayoutFactory(String title) {
        this.title = title;
    }

    @Override
    public Layout getLayout() {
        AddViewLayout componentAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/project/component.png"));

        Layout topPanel = createTopPanel();
        if (topPanel != null) {
            componentAddLayout.addTopControls(topPanel);
        }
        
        VerticalLayout layout = new VerticalLayout();
        
        Label organizationHeader = new Label("Component Information");
        organizationHeader.setStyleName("h2");
        layout.addComponent(organizationHeader);

        informationLayout = new ComponentInformationLayout();
        informationLayout.getLayout().setWidth("100%");
        layout.addComponent(informationLayout.getLayout());
        
        componentAddLayout.addBody(layout);

        Layout bottomPanel = createBottomPanel();
        if (bottomPanel != null) {
            componentAddLayout.addBottomControls(bottomPanel);
        }

        return componentAddLayout;
    }

    @Override
    public void attachField(Object propertyId, Field field) {
        informationLayout.attachField(propertyId, field);
    }

    protected abstract Layout createTopPanel();

    protected abstract Layout createBottomPanel();

    @SuppressWarnings("serial")
    public static class ComponentInformationLayout implements IFormLayoutFactory {

        private GridFormLayoutHelper informationLayout;

        @Override
        public Layout getLayout() {
            informationLayout = new GridFormLayoutHelper(2, 3);
            informationLayout.getLayout().setWidth("100%");
            VerticalLayout layout = new VerticalLayout();
            layout.addComponent(informationLayout.getLayout());
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field field) {
            if (propertyId.equals("componentname")) {
                informationLayout.addComponent(field, "Component Name", 0, 0, 2, "100%");
            } else if (propertyId.equals("description")) {
                informationLayout.addComponent(field, "Description", 0, 1, 2, "100%");
            } else if (propertyId.equals("userlead")) {
                informationLayout.addComponent(field, "Lead", 0, 2);
            }
        }
    }
    
}
