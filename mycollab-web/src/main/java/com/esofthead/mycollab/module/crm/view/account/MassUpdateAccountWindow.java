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
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.localization.AccountI18nEnum;
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

public class MassUpdateAccountWindow extends MassUpdateWindow<Account> {
	private static final long serialVersionUID = 1L;

	private final Account account;
	private final EditForm updateForm;
	private final ReadViewLayout accountAddLayout;
	private final VerticalLayout layout;

	public MassUpdateAccountWindow(final String title,
			final AccountListPresenter presenter) {
		super(title, presenter);
		this.setWidth("1000px");

		this.setIcon(MyCollabResource.newResource("icons/18/account.png"));

		this.accountAddLayout = new ReadViewLayout(null, false);

		this.account = new Account();

		this.layout = this.getLayout();

		this.updateForm = new EditForm();

		this.updateForm.setItemDataSource(new BeanItem<Account>(this.account));

		this.accountAddLayout.addBody(this.updateForm);

		this.addComponent(this.accountAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new MassUpdateAccountFormLayoutFactory());
			this.setFormFieldFactory(new AccountEditFormFieldFactory(
					MassUpdateAccountWindow.this.account));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateAccountFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;
			private GridFormLayoutHelper addressLayout;

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

				this.addressLayout = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);
				final Label addressHeader = new Label("Address Information");
				addressHeader.setStyleName("h2");
				formLayout.addComponent(addressHeader);
				this.addressLayout.getLayout().setWidth("100%");
				this.addressLayout.getLayout().setMargin(false);
				this.addressLayout.getLayout().setSpacing(false);
				this.addressLayout.getLayout().addStyleName(
						"colored-gridlayout");
				formLayout.addComponent(this.addressLayout.getLayout());

				formLayout.addComponent(MassUpdateAccountWindow.this.layout);

				return formLayout;
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {
				this.informationLayout.addComponent(propertyId
						.equals("industry"), field, LocalizationHelper
						.getMessage(AccountI18nEnum.FORM_INDUSTRY), 0, 0);

				this.informationLayout.addComponent(propertyId.equals("type"),
						field, LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_TYPE), 1, 0);
				this.informationLayout.addComponent(propertyId
						.equals("ownership"), field, LocalizationHelper
						.getMessage(AccountI18nEnum.FORM_OWNERSHIP), 0, 1);

				this.informationLayout.addComponent(propertyId
						.equals("assignuser"), field, LocalizationHelper
						.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 1, 1);

				this.addressLayout.addComponent(propertyId.equals("city"),
						field, LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_BILLING_CITY),
						0, 0);

				this.addressLayout.addComponent(propertyId
						.equals("shippingcity"), field, LocalizationHelper
						.getMessage(AccountI18nEnum.FORM_SHIPPING_CITY), 1, 0);

				this.addressLayout
						.addComponent(
								propertyId.equals("state"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_STATE),
								0, 1);

				this.addressLayout
						.addComponent(
								propertyId.equals("postalcode"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_POSTAL_CODE),
								1, 1);

				this.addressLayout
						.addComponent(
								propertyId.equals("billingcountry"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_COUNTRY),
								0, 2);
				this.addressLayout.addComponent(propertyId
						.equals("shippingcountry"), field, LocalizationHelper
						.getMessage(AccountI18nEnum.FORM_SHIPPING_COUNTRY), 1,
						2);

				this.addressLayout.addComponent(propertyId
						.equals("shippingstate"), field, LocalizationHelper
						.getMessage(AccountI18nEnum.FORM_SHIPPING_STATE), 0, 3);
				this.addressLayout
						.addComponent(
								propertyId.equals("shippingpostalcode"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_POSTAL_CODE),
								1, 3);
			}
		}
	}

	@Override
	protected Account getItem() {
		return this.account;
	}
}
