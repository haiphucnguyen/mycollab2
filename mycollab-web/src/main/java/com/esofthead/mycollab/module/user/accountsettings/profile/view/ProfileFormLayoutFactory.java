/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.profile.view;

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
@SuppressWarnings("serial")
public abstract class ProfileFormLayoutFactory implements IFormLayoutFactory {

    private String title;
    private UserInformationLayout userInformationLayout;

    public ProfileFormLayoutFactory(String title) {
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
        userInformationLayout.getLayout().setWidth("100%");
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

        private GridFormLayoutHelper basicInformationLayout;
        private GridFormLayoutHelper advancedInformationLayout;
        private GridFormLayoutHelper contactInformationLayout;

        @Override
        public Layout getLayout() {
            VerticalLayout layout = new VerticalLayout();
            Label organizationHeader = new Label("Basic User Information");
            organizationHeader.setStyleName("h2");
            layout.addComponent(organizationHeader);

            basicInformationLayout = new GridFormLayoutHelper(2, 6);
            basicInformationLayout.getLayout().setWidth("100%");

            layout.addComponent(basicInformationLayout.getLayout());
            
            Label contactHeader = new Label("Contact User Information");
            contactHeader.setStyleName("h2");
            layout.addComponent(contactHeader);

            contactInformationLayout = new GridFormLayoutHelper(2, 6);
            contactInformationLayout.getLayout().setWidth("100%");

            layout.addComponent(contactInformationLayout.getLayout());
            
            Label advancedHeader = new Label("Advanced User Information");
            advancedHeader.setStyleName("h2");
            layout.addComponent(advancedHeader);

            advancedInformationLayout = new GridFormLayoutHelper(2, 2);
            advancedInformationLayout.getLayout().setWidth("100%");

            layout.addComponent(advancedInformationLayout.getLayout());
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field field) {
            if (propertyId.equals("firstname")) {
                basicInformationLayout.addComponent(field, "First Name", 0, 0);
            } else if (propertyId.equals("lastname")) {
                basicInformationLayout.addComponent(field, "Last Name", 0, 1);
            } else if (propertyId.equals("nickname")) {
                basicInformationLayout.addComponent(field, "Nick Name", 1, 0);
            } else if (propertyId.equals("dateofbirth")) {
                basicInformationLayout.addComponent(field, "Birthday", 1, 1);
            } else if (propertyId.equals("email")) {
                basicInformationLayout.addComponent(field, "Email", 0, 2);
            } else if (propertyId.equals("isadmin")) {
                basicInformationLayout.addComponent(field, "Is Admin", 1, 2);
            } else if (propertyId.equals("company")) {
            	advancedInformationLayout.addComponent(field, "Company", 0, 0);
            } else if (propertyId.equals("country")) {
            	advancedInformationLayout.addComponent(field, "Country", 0, 1);
			} else if (propertyId.equals("timezone")) {
            	advancedInformationLayout.addComponent(field, "Timezone", 1, 1);
			} else if (propertyId.equals("website")) {
				advancedInformationLayout.addComponent(field, "Website", 1, 0);
            } else if (propertyId.equals("workphone")) {
            	contactInformationLayout.addComponent(field, "Work phone", 0, 0);
			} else if (propertyId.equals("homephone")) {
				contactInformationLayout.addComponent(field, "Home phone", 0, 1);
			} else if (propertyId.equals("facebookaccount")) {
				contactInformationLayout.addComponent(field, "Facebook", 1, 0);
			} else if (propertyId.equals("twitteraccount")) {
				contactInformationLayout.addComponent(field, "Twitter", 1, 1);
			} else if (propertyId.equals("skypecontact")) {
				contactInformationLayout.addComponent(field, "Skype", 0, 2);
			}
        }
    }
}
