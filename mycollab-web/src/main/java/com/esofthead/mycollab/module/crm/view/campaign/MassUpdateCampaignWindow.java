package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.view.contact.ContactEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MassUpdateCampaignWindow extends MassUpdateWindow<CampaignWithBLOBs> {
	private static final long serialVersionUID = 1L;
	private CampaignWithBLOBs campaign;
	private final EditForm updateForm;
	private ReadViewLayout campaginAddLayout;
	private VerticalLayout layout;

	public MassUpdateCampaignWindow(String title,CampaignListPresenter presenter) {
		super(title, presenter);
		
		this.setWidth("1000px");
		
		this.setIcon(new ThemeResource("icons/18/crm/campaign.png"));
		
		campaginAddLayout = new ReadViewLayout(null);

		campaign = new CampaignWithBLOBs();

		layout = getLayout();

		updateForm = new EditForm();

		updateForm.setItemDataSource(new BeanItem<CampaignWithBLOBs>(campaign));

		campaginAddLayout.addComponent(updateForm);

		this.addComponent(campaginAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			setFormLayoutFactory(new MassUpdateContactFormLayoutFactory());
			setFormFieldFactory(new CampaignEditFormFieldFactory(campaign));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateContactFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;
			private GridFormLayoutHelper campaignGoal;

			@Override
			public Layout getLayout() {
				VerticalLayout formLayout = new VerticalLayout();

				Label organizationHeader = new Label("Campaign Information");
				organizationHeader.setStyleName("h2");
				formLayout.addComponent(organizationHeader);

				informationLayout = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);

				informationLayout.getLayout().setWidth("100%");
				informationLayout.getLayout().setMargin(false);
				informationLayout.getLayout().setSpacing(false);
				informationLayout.getLayout()
						.addStyleName("colored-gridlayout");
				formLayout.addComponent(informationLayout.getLayout());

				campaignGoal = new GridFormLayoutHelper(2, 6, "100%", "167px",
						Alignment.MIDDLE_LEFT);
				Label campaignMoreInfo = new Label("Campaign Goal");
				campaignMoreInfo.setStyleName("h2");
				formLayout.addComponent(campaignMoreInfo);
				campaignGoal.getLayout().setWidth("100%");
				campaignGoal.getLayout().setMargin(false);
				campaignGoal.getLayout().setSpacing(false);
				campaignGoal.getLayout().addStyleName("colored-gridlayout");
				formLayout.addComponent(campaignGoal.getLayout());

				formLayout.addComponent(layout);
				formLayout.addStyleName("v-csslayout v-csslayout-readview-layout-body readview-layout-body");

				return formLayout;
			}
//			status, type, assign user, currency
			@Override
			public void attachField(final Object propertyId, final Field field) {
				
	            informationLayout.addComponent(propertyId.equals("status"), field,
	                    "Status", 0, 0);
	            
	            informationLayout.addComponent(propertyId.equals("type"), field,
	                    "Type", 1, 0);
	            
	            if (propertyId.equals("assignuser")) {
	                informationLayout.addComponent(field, "Assigned to", 0, 1);
	            }
	            
	            campaignGoal.addComponent(propertyId.equals("currencyid"), field,
	                    "Currency", 0, 0);
			}
		}
	}

	@Override
	protected CampaignWithBLOBs getItem() {
		return campaign;
	}
}
