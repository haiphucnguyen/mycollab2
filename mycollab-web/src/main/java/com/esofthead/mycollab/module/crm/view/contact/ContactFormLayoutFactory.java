package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class ContactFormLayoutFactory implements IFormLayoutFactory {

    private static final long serialVersionUID = 1L;
    private String title;
    private ContactInformationLayout informationLayout;

    public ContactFormLayoutFactory(String title) {
        this.title = title;
    }

    @Override
    public Layout getLayout() {
        informationLayout = new ContactInformationLayout();
        AddViewLayout contactAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/crm/contact.png"));

        Layout topPanel = createTopPanel();
        if (topPanel != null) {
            contactAddLayout.addTopControls(topPanel);
        }

        contactAddLayout.addBody(informationLayout.getLayout());
        
        Layout bottomPanel = createBottomPanel();
        if (bottomPanel != null) {
            
        }contactAddLayout.addBottomControls(bottomPanel);
        
        return contactAddLayout;
    }

    protected abstract Layout createTopPanel();

    protected abstract Layout createBottomPanel();

    @Override
    public void attachField(Object propertyId, Field field) {
        informationLayout.attachField(propertyId, field);
    }

    public static class ContactInformationLayout implements IFormLayoutFactory {

        private VerticalLayout layout;
        private GridFormLayoutHelper informationLayout;
        private GridFormLayoutHelper addressLayout;
        private GridFormLayoutHelper descriptionLayout;

        @Override
        public Layout getLayout() {
            layout = new VerticalLayout();
            Label organizationHeader = new Label("Contact Information");
            organizationHeader.setStyleName("h2");
            layout.addComponent(organizationHeader);

            informationLayout = new GridFormLayoutHelper(2, 9);
            informationLayout.getLayout().setWidth("900px");
            layout.addComponent(informationLayout.getLayout());

            Label addressHeader = new Label("Address Information");
            addressHeader.setStyleName("h2");
            layout.addComponent(addressHeader);
            addressLayout = new GridFormLayoutHelper(2, 5);
            addressLayout.getLayout().setWidth("900px");
            layout.addComponent(addressLayout.getLayout());

            descriptionLayout = new GridFormLayoutHelper(2, 1);
            Label descHeader = new Label("Description");
            descHeader.setStyleName("h2");
            layout.addComponent(descHeader);
            descriptionLayout.getLayout().setWidth("900px");
            layout.addComponent(descriptionLayout.getLayout());
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field field) {
            if (propertyId.equals("firstname")) {
                informationLayout.addComponent(field, "First Name", 0, 0);
            } else if (propertyId.equals("lastname")) {
                informationLayout.addComponent(field, "Last Name", 0, 1);
            } else if (propertyId.equals("accountId")) {
                informationLayout.addComponent(field, "Account", 0, 2);
            } else if (propertyId.equals("title")) {
                informationLayout.addComponent(field, "Title", 0, 3);
            } else if (propertyId.equals("department")) {
                informationLayout.addComponent(field, "Department", 0, 4);
            } else if (propertyId.equals("email")) {
                informationLayout.addComponent(field, "Email", 0, 5);
            } else if (propertyId.equals("assistant")) {
                informationLayout.addComponent(field, "Assistant", 0, 6);
            } else if (propertyId.equals("assistantphone")) {
                informationLayout.addComponent(field, "Assistant Phone", 0, 7);
            } else if (propertyId.equals("leadsource")) {
                informationLayout.addComponent(field, "Leade Source", 0, 8);
            } else if (propertyId.equals("officephone")) {
                informationLayout.addComponent(field, "Phone Office", 1, 0);
            } else if (propertyId.equals("mobile")) {
                informationLayout.addComponent(field, "Mobile", 1, 1);
            } else if (propertyId.equals("homephone")) {
                informationLayout.addComponent(field, "Home Phone", 1, 2);
            } else if (propertyId.equals("otherphone")) {
                informationLayout.addComponent(field, "Other Phone", 1, 3);
            } else if (propertyId.equals("fax")) {
                informationLayout.addComponent(field, "Fax", 1, 4);
            } else if (propertyId.equals("birthday")) {
                informationLayout.addComponent(field, "Birthday", 1, 5);
            } else if (propertyId.equals("iscallable")) {
                informationLayout.addComponent(field, "Callable", 1, 6);
            } else if (propertyId.equals("assignuser")) {
                informationLayout.addComponent(field, "Assign User", 1, 7);
            } else if (propertyId.equals("primaddress")) {
                addressLayout.addComponent(field, "Address", 0, 0);
            } else if (propertyId.equals("primcity")) {
                addressLayout.addComponent(field, "City", 0, 1);
            } else if (propertyId.equals("primstate")) {
                addressLayout.addComponent(field, "State", 0, 2);
            } else if (propertyId.equals("primpostalcode")) {
                addressLayout.addComponent(field, "Postal Code", 0, 3);
            } else if (propertyId.equals("primcountry")) {
                addressLayout.addComponent(field, "Country", 0, 4);
            } else if (propertyId.equals("otheraddress")) {
                addressLayout.addComponent(field, "Other Address", 1, 0);
            } else if (propertyId.equals("othercity")) {
                addressLayout.addComponent(field, "Other City", 1, 1);
            } else if (propertyId.equals("otherstate")) {
                addressLayout.addComponent(field, "Other State", 1, 2);
            } else if (propertyId.equals("otherpostalcode")) {
                addressLayout.addComponent(field, "Other Postal Code", 1, 3);
            } else if (propertyId.equals("othercountry")) {
                addressLayout.addComponent(field, "Other Country", 1, 4);
            } else if (propertyId.equals("description")) {
                descriptionLayout.addComponent(field, "Description", 0, 0, 2,
                        UIConstants.DEFAULT_2XCONTROL_WIDTH,
                        UIConstants.DEFAULT_2XCONTROL_HEIGHT);
            }
        }
    }
}
