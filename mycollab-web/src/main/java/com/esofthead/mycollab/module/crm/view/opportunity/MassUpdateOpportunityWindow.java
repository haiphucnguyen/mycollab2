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
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MassUpdateOpportunityWindow extends MassUpdateWindow<Opportunity> {
	private static final long serialVersionUID = 1L;

	private final Opportunity opportunity;
	private final EditForm updateForm;
	private final ReadViewLayout opportunityAddLayout;
	private final VerticalLayout layout;

	public MassUpdateOpportunityWindow(final String title,
			final OpportunityListPresenter presenter) {
		super(title, presenter);
		this.setWidth("1000px");

		this.setIcon(MyCollabResource
				.newResource("icons/18/crm/opportunity.png"));

		this.opportunityAddLayout = new ReadViewLayout(null, false);

		this.opportunity = new Opportunity();

		this.layout = this.getLayout();

		this.updateForm = new EditForm();

		this.updateForm.setItemDataSource(new BeanItem<Opportunity>(
				this.opportunity));

		this.opportunityAddLayout.addBody(this.updateForm);

		this.addComponent(this.opportunityAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new MassUpdateAccountFormLayoutFactory());
			this.setFormFieldFactory(new OpportunityEditFormFieldFactory(
					MassUpdateOpportunityWindow.this.opportunity));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateAccountFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				final VerticalLayout formLayout = new VerticalLayout();

				final Label organizationHeader = new Label(
						"Account Information");
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

				formLayout
						.addComponent(MassUpdateOpportunityWindow.this.layout);

				return formLayout;
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("opportunityname")) {
					this.informationLayout.addComponent(field,
							"Opportunity Name", 0, 0);
				} else if (propertyId.equals("currencyid")) {
					this.informationLayout
							.addComponent(field, "Currency", 0, 1);
				} else if (propertyId.equals("amount")) {
					this.informationLayout.addComponent(field, "Amount", 0, 2);
				} else if (propertyId.equals("salesstage")) {
					this.informationLayout.addComponent(field, "Sales Stage",
							0, 3);
				} else if (propertyId.equals("probability")) {
					this.informationLayout.addComponent(field,
							"Probability (%)", 0, 4);
				} else if (propertyId.equals("nextstep")) {
					this.informationLayout.addComponent(field, "Next Step", 0,
							5);
				} else if (propertyId.equals("accountid")) {
					this.informationLayout.addComponent(field, "Account Name",
							1, 0);
				} else if (propertyId.equals("expectedcloseddate")) {
					this.informationLayout.addComponent(field,
							"Expected Close Date", 1, 1);
				} else if (propertyId.equals("opportunitytype")) {
					this.informationLayout.addComponent(field, "Type", 1, 2);
				} else if (propertyId.equals("source")) {
					this.informationLayout.addComponent(field, "Lead Source",
							1, 3);
				} else if (propertyId.equals("campaignid")) {
					this.informationLayout
							.addComponent(field, "Campaign", 1, 4);
				} else if (propertyId.equals("assignuser")) {
					this.informationLayout
							.addComponent(
									field,
									LocalizationHelper
											.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
									1, 5);
				}
			}
		}
	}

	@Override
	protected Opportunity getItem() {
		return this.opportunity;
	}
}
