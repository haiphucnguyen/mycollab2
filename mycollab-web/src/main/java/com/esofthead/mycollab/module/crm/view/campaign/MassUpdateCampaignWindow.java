/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateLayout;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
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
	private final MassUpdateLayout campaginAddLayout;
	private final VerticalLayout layout;

	public MassUpdateCampaignWindow(final String title,
			final CampaignListPresenter presenter) {
		super(title, presenter);

		this.setWidth("1000px");

		this.setIcon(MyCollabResource.newResource("icons/18/crm/campaign.png"));

		this.campaginAddLayout = new MassUpdateLayout();

		this.campaign = new CampaignWithBLOBs();

		this.layout = this.getLayout();

		this.updateForm = new EditForm();

		this.updateForm.setItemDataSource(new BeanItem<CampaignWithBLOBs>(
				this.campaign));

		this.campaginAddLayout.addBody(this.updateForm);

		this.setContent(this.campaginAddLayout);
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

				this.informationLayout.addComponent(propertyId
						.equals("assignuser"), field, LocalizationHelper
						.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 0, 0);

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
