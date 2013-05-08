package com.esofthead.mycollab.module.crm.view.campaign;

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

public abstract class CampaignFormLayoutFactory implements IFormLayoutFactory {

    private static final long serialVersionUID = 1L;
    private String title;
    private CampaignInformationLayout campaignLayout;

    public CampaignFormLayoutFactory(String title) {
        this.title = title;
    }

    @Override
    public Layout getLayout() {
        AddViewLayout campaignFormLayout = new AddViewLayout(title, new ThemeResource("icons/48/crm/campaign.png"));

        Layout topPanel = createTopPanel();
        if (topPanel != null) {
            campaignFormLayout.addTopControls(createTopPanel());
        }

        campaignLayout = new CampaignInformationLayout();
        campaignFormLayout.addBody(campaignLayout.getLayout());

        Layout bottomPanel = createBottomPanel();
        if (bottomPanel != null) {
            campaignFormLayout.addBottomControls(bottomPanel);
        }


        return campaignFormLayout;
    }

    protected abstract Layout createTopPanel();

    protected abstract Layout createBottomPanel();

    @Override
    public void attachField(Object propertyId, Field field) {
        campaignLayout.attachField(propertyId, field);
    }

    public static class CampaignInformationLayout implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		
		private VerticalLayout layout;
        private GridFormLayoutHelper informationLayout;
        private GridFormLayoutHelper campaignGoal;
        private GridFormLayoutHelper descriptionLayout;

        @Override
        public Layout getLayout() {
            layout = new VerticalLayout();
            Label organizationHeader = new Label("Campaign Information");
            organizationHeader.setStyleName("h2");
            layout.addComponent(organizationHeader);

            informationLayout = new GridFormLayoutHelper(2, 6);
            informationLayout.getLayout().setWidth("900px");
            layout.addComponent(informationLayout.getLayout());
            layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);

            campaignGoal = new GridFormLayoutHelper(2, 4);
            Label addressHeader = new Label("Campaign Goal");
            addressHeader.setStyleName("h2");
            layout.addComponent(addressHeader);
            campaignGoal.getLayout().setWidth("900px");
            layout.addComponent(campaignGoal.getLayout());
            layout.setComponentAlignment(campaignGoal.getLayout(),
					Alignment.BOTTOM_CENTER);

            descriptionLayout = new GridFormLayoutHelper(2, 1);
            Label descHeader = new Label("Description");
            descHeader.setStyleName("h2");

            layout.addComponent(descHeader);
            layout.addComponent(descriptionLayout.getLayout());
            descriptionLayout.getLayout().setWidth("900px");
            layout.setComponentAlignment(descriptionLayout.getLayout(),
					Alignment.BOTTOM_CENTER);
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field field) {
            informationLayout.addComponent(propertyId.equals("campaignname"),
                    field, "Name", 0, 0);
            informationLayout.addComponent(propertyId.equals("startdate"), field,
                    "Start Date", 0, 1);
            informationLayout.addComponent(propertyId.equals("enddate"), field,
                    "End Date", 0, 2);
            informationLayout.addComponent(propertyId.equals("status"), field,
                    "Status", 1, 0);
            informationLayout.addComponent(propertyId.equals("type"), field,
                    "Type", 1, 1);

            campaignGoal.addComponent(propertyId.equals("currencyid"), field,
                    "Currency", 0, 0);
            campaignGoal.addComponent(propertyId.equals("budget"), field, "Budget",
                    1, 1);
            campaignGoal.addComponent(propertyId.equals("expectedcost"), field,
                    "Expected Cost", 0, 1);
            campaignGoal.addComponent(propertyId.equals("actualcost"), field,
                    "Actual Cost", 1, 2);
            campaignGoal.addComponent(propertyId.equals("expectedrevenue"), field,
                    "Expected Revenue", 0, 2);

            if (propertyId.equals("assignuser")) {
                informationLayout.addComponent(field, "Assigned to", 1, 2);
            } else if (propertyId.equals("description")) {
                descriptionLayout.addComponent(field,
                        "Description", 0, 0, 2, UIConstants.DEFAULT_2XCONTROL_WIDTH,
                        UIConstants.DEFAULT_2XCONTROL_HEIGHT);
            }
        }
    }
}
