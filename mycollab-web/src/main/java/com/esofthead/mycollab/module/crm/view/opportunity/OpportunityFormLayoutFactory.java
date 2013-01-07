package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class OpportunityFormLayoutFactory implements
        IFormLayoutFactory {

    private static final long serialVersionUID = 1L;
    private String title;
    private OpportunityInformationLayout opportunityInformation;

    public OpportunityFormLayoutFactory(String title) {
        this.title = title;
    }

    @Override
    public Layout getLayout() {
        AddViewLayout opportunityAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/crm/opportunity.png"));

        Layout topPanel = createTopPanel();
        if (topPanel != null) {
            opportunityAddLayout.addTopControls(topPanel);
        }


        opportunityInformation = new OpportunityInformationLayout();
        opportunityAddLayout.addBody(opportunityInformation.getLayout());

        Layout bottomPanel = createBottomPanel();
        if (bottomPanel != null) {
            opportunityAddLayout.addBottomControls(bottomPanel);
        }

        return opportunityAddLayout;
    }

    protected abstract Layout createTopPanel();

    protected abstract Layout createBottomPanel();

    @Override
    public void attachField(Object propertyId, Field field) {
        opportunityInformation.attachField(propertyId, field);
    }

    public static class OpportunityInformationLayout implements IFormLayoutFactory {

        protected VerticalLayout layout;
        protected GridFormLayoutHelper informationLayout;
        protected GridFormLayoutHelper descriptionLayout;

        @Override
        public Layout getLayout() {
            layout = new VerticalLayout();
            layout.setSpacing(true);

            Label organizationHeader = new Label("Account Information");
            organizationHeader.setStyleName("h2");
            layout.addComponent(organizationHeader);

            informationLayout = new GridFormLayoutHelper(2, 6);
            informationLayout.getLayout().setWidth("900px");
            layout.addComponent(informationLayout.getLayout());

            descriptionLayout = new GridFormLayoutHelper(2, 1);
            descriptionLayout.getLayout().setWidth("900px");
            Label descHeader = new Label("Description");
            descHeader.setStyleName("h2");
            layout.addComponent(descHeader);
            layout.addComponent(descriptionLayout.getLayout());
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field field) {
            if (propertyId.equals("opportunityname")) {
                informationLayout.addComponent(field, "Opportunity Name", 0, 0);
            } else if (propertyId.equals("currencyid")) {
                informationLayout.addComponent(field, "Currency", 0, 1);
            } else if (propertyId.equals("amount")) {
                informationLayout.addComponent(field, "Amount", 0, 2);
            } else if (propertyId.equals("salesstage")) {
                informationLayout.addComponent(field, "Sales Stage", 0, 3);
            } else if (propertyId.equals("probability")) {
                informationLayout.addComponent(field, "Probability (%)", 0, 4);
            } else if (propertyId.equals("nextstep")) {
                informationLayout.addComponent(field, "Next Step", 0, 5);
            } else if (propertyId.equals("accountid")) {
                informationLayout.addComponent(field, "Account Name", 1, 0);
            } else if (propertyId.equals("expectedcloseddate")) {
                informationLayout.addComponent(field, "Expected Close Date", 1, 1);
            } else if (propertyId.equals("opportunitytype")) {
                informationLayout.addComponent(field, "Type", 1, 2);
            } else if (propertyId.equals("source")) {
                informationLayout.addComponent(field, "Lead Source", 1, 3);
            } else if (propertyId.equals("campaignid")) {
                informationLayout.addComponent(field, "Campaign", 1, 4);
            } else if (propertyId.equals("assignuser")) {
                informationLayout.addComponent(field, "Assigned User", 1, 5);
            } else if (propertyId.equals("description")) {
                descriptionLayout.addComponent(field, "Description", 0, 0, 2,
                        UIConstants.DEFAULT_2XCONTROL_WIDTH,
                        UIConstants.DEFAULT_2XCONTROL_HEIGHT);
            }
        }
    }
}
