package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class AccountFormLayoutFactory implements IFormLayoutFactory {

    private static final long serialVersionUID = 1L;
    private String title;
    private AccountInformationLayout informationLayout;

    public AccountFormLayoutFactory(String title) {
        this.title = title;
    }

    @Override
    public Layout getLayout() {
        AddViewLayout accountAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/crm/account.png"));

        Layout topPanel = createTopPanel();
        if (topPanel != null) {
            accountAddLayout.addTopControls(topPanel);
        }

        informationLayout = new AccountInformationLayout();
        accountAddLayout.addBody(informationLayout.getLayout());

        Layout bottomPanel = createBottomPanel();
        if (bottomPanel != null) {
            accountAddLayout.addBottomControls(bottomPanel);
        }

        return accountAddLayout;
    }

    protected abstract Layout createTopPanel();

    protected abstract Layout createBottomPanel();

    @Override
    public void attachField(Object propertyId, Field field) {
        informationLayout.attachField(propertyId, field);
    }

    @SuppressWarnings("serial")
    public static class AccountInformationLayout implements IFormLayoutFactory {

        private VerticalLayout layout;
        private GridFormLayoutHelper informationLayout;
        private GridFormLayoutHelper addressLayout;
        private GridFormLayoutHelper descriptionLayout;

        @Override
        public Layout getLayout() {
            layout = new VerticalLayout();
            Label organizationHeader = new Label("Account Information");
            organizationHeader.setStyleName("h2");
            layout.addComponent(organizationHeader);

            informationLayout = new GridFormLayoutHelper(2, 6);
            informationLayout.getLayout().setWidth("900px");
            layout.addComponent(informationLayout.getLayout());
            layout.setComponentAlignment(informationLayout.getLayout(),
                    Alignment.BOTTOM_CENTER);

            addressLayout = new GridFormLayoutHelper(2, 5);
            Label addressHeader = new Label("Address Information");
            addressHeader.setStyleName("h2");
            layout.addComponent(addressHeader);
            addressLayout.getLayout().setWidth("900px");
            layout.addComponent(addressLayout.getLayout());
            layout.setComponentAlignment(addressLayout.getLayout(),
                    Alignment.BOTTOM_CENTER);

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
            informationLayout.addComponent(propertyId.equals("accountname"), field,
                    "Account Name", 0, 0);
            informationLayout.addComponent(propertyId.equals("phoneoffice"), field,
                    "Phone Office", 1, 0);
            informationLayout.addComponent(propertyId.equals("website"), field,
                    "Website", 0, 1);
            informationLayout.addComponent(propertyId.equals("fax"), field, "Fax",
                    1, 1);
            informationLayout.addComponent(propertyId.equals("numemployees"),
                    field, "Employees", 0, 2);
            informationLayout.addComponent(propertyId.equals("alternatephone"),
                    field, "Other Phone", 1, 2);
            informationLayout.addComponent(propertyId.equals("industry"), field,
                    "Industry", 0, 3);
            informationLayout.addComponent(propertyId.equals("email"), field,
                    "Email", 1, 3);
            informationLayout.addComponent(propertyId.equals("type"), field,
                    "Type", 0, 4);
            informationLayout.addComponent(propertyId.equals("ownership"), field,
                    "Ownership", 1, 4);
            informationLayout.addComponent(propertyId.equals("assignuser"), field,
                    "Assign User", 0, 5);
            informationLayout.addComponent(propertyId.equals("annualrevenue"),
                    field, "Annual Revenue", 1, 5);

            addressLayout.addComponent(propertyId.equals("billingaddress"), field,
                    "Billing Address", 0, 0);
            addressLayout.addComponent(propertyId.equals("shippingaddress"), field,
                    "Shipping Address", 1, 0);
            addressLayout.addComponent(propertyId.equals("city"), field,
                    "Billing City", 0, 1);
            addressLayout.addComponent(propertyId.equals("shippingcity"), field,
                    "Shipping City", 1, 1);
            addressLayout.addComponent(propertyId.equals("state"), field,
                    "Billing State", 0, 2);
            addressLayout.addComponent(propertyId.equals("shippingstate"), field,
                    "Shipping State", 1, 2);
            addressLayout.addComponent(propertyId.equals("postalcode"), field,
                    "Billing Postal Code", 0, 3);
            addressLayout.addComponent(propertyId.equals("shippingpostalcode"),
                    field, "Shipping Postal Code", 1, 3);
            addressLayout.addComponent(propertyId.equals("billingcountry"), field,
                    "Billing Country", 0, 4);
            addressLayout.addComponent(propertyId.equals("shippingcountry"),
                    field, "Shipping Country", 1, 4);

            if (propertyId.equals("description")) {
                field.setSizeUndefined();
                descriptionLayout.addComponent(field, "Description", 0, 0, 2,
                        UIConstants.DEFAULT_2XCONTROL_WIDTH);
            }
        }
    }
}
