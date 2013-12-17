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
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public class MassUpdateLeadWindow extends MassUpdateWindow<Lead> {
	private static final long serialVersionUID = 1L;

	public MassUpdateLeadWindow(final String title,
			final LeadListPresenter presenter) {
		super(title, MyCollabResource.newResource("icons/18/crm/lead.png"),
				new Lead(), presenter);
	}

	@Override
	protected IFormLayoutFactory buildFormLayoutFactory() {
		return new MassUpdateLeadFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Lead> initBeanFormFieldFactory() {
		return new LeadEditFormFieldFactory<Lead>(updateForm);
	}

	private class MassUpdateLeadFormLayoutFactory implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;

		private GridFormLayoutHelper informationLayout;
		private GridFormLayoutHelper addressLayout;

		@Override
		public Layout getLayout() {
			final VerticalLayout formLayout = new VerticalLayout();
			formLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

			final Label organizationHeader = new Label("Contact Information");
			organizationHeader.setStyleName(UIConstants.H2_STYLE2);
			formLayout.addComponent(organizationHeader);

			this.informationLayout = new GridFormLayoutHelper(2, 6, "100%",
					"167px", Alignment.MIDDLE_LEFT);

			this.informationLayout.getLayout().setWidth("100%");
			this.informationLayout.getLayout().setMargin(false);
			this.informationLayout.getLayout().setSpacing(false);
			this.informationLayout.getLayout().addStyleName(
					"colored-gridlayout");
			formLayout.addComponent(this.informationLayout.getLayout());

			this.addressLayout = new GridFormLayoutHelper(2, 6, "100%",
					"167px", Alignment.MIDDLE_LEFT);
			final Label leadMoreInfo = new Label("More Information");
			leadMoreInfo.setStyleName(UIConstants.H2_STYLE2);
			formLayout.addComponent(leadMoreInfo);
			this.addressLayout.getLayout().setWidth("100%");
			this.addressLayout.getLayout().setMargin(false);
			this.addressLayout.getLayout().setSpacing(false);
			this.addressLayout.getLayout().addStyleName("colored-gridlayout");
			formLayout.addComponent(this.addressLayout.getLayout());

			formLayout.addComponent(buildButtonControls());

			return formLayout;
		}

		// Title, Account Name, Lead Source, Industry, Status, Assign User,
		// primary/other city, primary/other state, primary/other postal
		// code, primary/other country
		@Override
		public void attachField(final Object propertyId, final Field<?> field) {

			this.informationLayout.addComponent(propertyId.equals("title"),
					field, "Title", 0, 0);
			this.informationLayout.addComponent(
					propertyId.equals("accountname"), field, "Account Name", 1,
					0);
			this.informationLayout.addComponent(propertyId.equals("source"),
					field, "Lead Source", 0, 1);
			this.informationLayout.addComponent(propertyId.equals("industry"),
					field, "Industry", 1, 1);

			this.informationLayout.addComponent(propertyId.equals("status"),
					field, "Status", 0, 2);
			this.informationLayout.addComponent(
					propertyId.equals("assignuser"), field, LocalizationHelper
							.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 1,
					2);

			this.addressLayout.addComponent(propertyId.equals("primcity"),
					field, "City", 0, 0);
			this.addressLayout.addComponent(propertyId.equals("primstate"),
					field, "State", 1, 0);
			this.addressLayout.addComponent(
					propertyId.equals("primpostalcode"), field, "Postal Code",
					0, 1);
			this.addressLayout.addComponent(propertyId.equals("primcountry"),
					field, "Country", 1, 1);

			this.addressLayout.addComponent(propertyId.equals("othercity"),
					field, "Other City", 0, 2);
			this.addressLayout.addComponent(propertyId.equals("otherstate"),
					field, "Other State", 1, 2);
			this.addressLayout.addComponent(
					propertyId.equals("otherpostalcode"), field,
					"Other Postal Code", 0, 3);
			this.addressLayout.addComponent(propertyId.equals("othercountry"),
					field, "Other Country", 1, 3);
		}
	}
}
