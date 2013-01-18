/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public abstract class VersionFormLayoutFactory implements IFormLayoutFactory {

    private String title;
    private VersionInformationLayout informationLayout;

    public VersionFormLayoutFactory(String title) {
        this.title = title;
    }

    @Override
    public Layout getLayout() {
        AddViewLayout componentAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/project/version.png"));

        Layout topPanel = createTopPanel();
        if (topPanel != null) {
            componentAddLayout.addTopControls(topPanel);
        }

        informationLayout = new VersionInformationLayout();
        componentAddLayout.addBody(informationLayout.getLayout());

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
    public static class VersionInformationLayout implements IFormLayoutFactory {

        private GridFormLayoutHelper informationLayout;

        @Override
        public Layout getLayout() {
            informationLayout = new GridFormLayoutHelper(1, 2);
            VerticalLayout layout = new VerticalLayout();
            layout.addComponent(informationLayout.getLayout());
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field field) {
            if (propertyId.equals("versionname")) {
                informationLayout.addComponent(field, "Version Name", 0, 0);
            } else if (propertyId.equals("description")) {
                informationLayout.addComponent(field, "Description", 0, 1);
            }
        }
    }
}
