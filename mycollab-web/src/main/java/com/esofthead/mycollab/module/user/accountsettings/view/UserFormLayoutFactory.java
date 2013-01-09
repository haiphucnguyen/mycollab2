/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public abstract class UserFormLayoutFactory implements IFormLayoutFactory {

    private String title;
    private UserInformationLayout userInformationLayout;

    public UserFormLayoutFactory(String title) {
        this.title = title;
    }

    @Override
    public Layout getLayout() {
        AddViewLayout userAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/user/user.png"));

        Layout topPanel = createTopPanel();
        if (topPanel != null) {
            userAddLayout.addTopControls(topPanel);
        }

        userInformationLayout = new UserInformationLayout();
        userAddLayout.addBody(userInformationLayout.getLayout());

        Layout bottomPanel = createBottomPanel();
        if (bottomPanel != null) {
            userAddLayout.addBottomControls(bottomPanel);
        }

        return userAddLayout;
    }

    protected abstract Layout createTopPanel();

    protected abstract Layout createBottomPanel();

    @Override
    public void attachField(Object propertyId, Field field) {
        userInformationLayout.attachField(propertyId, field);
    }

    public static class UserInformationLayout implements IFormLayoutFactory {

        private GridFormLayoutHelper informationLayout;

        @Override
        public Layout getLayout() {
            VerticalLayout layout = new VerticalLayout();
            Label organizationHeader = new Label("User Information");
            organizationHeader.setStyleName("h2");
            layout.addComponent(organizationHeader);

            informationLayout = new GridFormLayoutHelper(2, 6);
            informationLayout.getLayout().setWidth("900px");
            
            layout.addComponent(informationLayout.getLayout());
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field field) {
            if (propertyId.equals("firstname")) {
                informationLayout.addComponent(field, "First Name", 0, 0);
            } else if (propertyId.equals("lastname")) {
                informationLayout.addComponent(field, "Last Name", 0, 1);
            } else if (propertyId.equals("nickname")) {
                informationLayout.addComponent(field, "Nick Name", 1, 0);
            } else if (propertyId.equals("dateofbirth")) {
                informationLayout.addComponent(field, "Birthday", 1, 1);
            } else if (propertyId.equals("company")) {
                informationLayout.addComponent(field, "Company", 0, 2);
            } else if (propertyId.equals("website")) {
                informationLayout.addComponent(field, "Website", 1, 2);
            }
        }
    }
}
