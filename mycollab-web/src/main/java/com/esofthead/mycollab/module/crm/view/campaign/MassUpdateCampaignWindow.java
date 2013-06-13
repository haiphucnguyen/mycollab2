package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.Contact;
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

public class MassUpdateCampaignWindow extends
		MassUpdateWindow<CampaignWithBLOBs> {
	private static final long serialVersionUID = 1L;
	private final CampaignWithBLOBs campaign;
	private final EditForm updateForm;
	private final ReadViewLayout campaginAddLayout;
	private final VerticalLayout layout;

	public MassUpdateCampaignWindow(final String title,
			final CampaignListPresenter presenter) {
		super(title, presenter);

		this.setWidth("1000px");

		this.setIcon(new ThemeResource("icons/18/crm/campaign.png"));

		this.campaginAddLayout = new ReadViewLayout(null, false);

		this.campaign = new CampaignWithBLOBs();

		this.layout = this.getLayout();

		this.updateForm = new EditForm();

		this.updateForm.setItemDataSource(new BeanItem<CampaignWithBLOBs>(
				this.campaign));

		this.campaginAddLayout.addBody(this.updateForm);

		this.addComponent(this.campaginAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new MassUpdateContactFormLayoutFactory());
			this.setFormFieldFactory(new CampaignEditFormFieldFactory(
					MassUpdateCampaignWindow.this.campaign));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateContactFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;
			private GridFormLayoutHelper campaignGoal;

			@Override
			public Layout getLayout() {
				final VerticalLayout formLayout = new VerticalLayout();

				final Label organizationHeader = new Label(
						"Campaign Information");
				organizationHeader.setStyleName("h2");
				formLayout.addComponent(organizationHeader);

				this.informationLayout = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);

				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().setSpacing(false);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");
				formLayout.addComponent(this.informationLayout.getLayout());

				this.campaignGoal = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);
				final Label campaignMoreInfo = new Label("Campaign Goal");
				campaignMoreInfo.setStyleName("h2");
				formLayout.addComponent(campaignMoreInfo);
				this.campaignGoal.getLayout().setWidth("100%");
				this.campaignGoal.getLayout().setMargin(false);
				this.campaignGoal.getLayout().setSpacing(false);
				this.campaignGoal.getLayout()
						.addStyleName("colored-gridlayout");
				formLayout.addComponent(this.campaignGoal.getLayout());

				formLayout.addComponent(MassUpdateCampaignWindow.this.layout);

				return formLayout;
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {

				this.informationLayout.addComponent(
						propertyId.equals("assignuser"), field, "Assigned to",
						0, 0);

				this.informationLayout.addComponent(
						propertyId.equals("status"), field, "Status", 1, 0);

				if (propertyId.equals("type")) {
					this.informationLayout.addComponent(field, "Type", 0, 1, 2,
							"297px", Alignment.TOP_LEFT);
				}

				if (propertyId.equals("currencyid")) {
					this.campaignGoal.addComponent(field, "Currency", 0, 0,
							"297px");
				}

			}
		}
	}

	@Override
	protected CampaignWithBLOBs getItem() {
		return this.campaign;
	}
}
