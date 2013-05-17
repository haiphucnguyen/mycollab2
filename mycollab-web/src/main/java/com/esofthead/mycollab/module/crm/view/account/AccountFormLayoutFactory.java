package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.crm.localization.AccountI18nEnum;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class AccountFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private final String title;
	private AccountInformationLayout informationLayout;

	public AccountFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout accountAddLayout = new AddViewLayout(title,
				new ThemeResource("icons/48/crm/account.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			accountAddLayout.addTopControls(topPanel);
		}

		informationLayout = new AccountInformationLayout(false);
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
		private final boolean isReadMode;

		public AccountInformationLayout(boolean isReadMode) {
			this.isReadMode = isReadMode;
		}

		@Override
		public Layout getLayout() {
			layout = new VerticalLayout();
			Label organizationHeader = new Label("Account Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 6, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			informationLayout.getLayout().setWidth("100%");
			informationLayout.getLayout().setMargin(false);
			informationLayout.getLayout().setSpacing(false);
			layout.addComponent(informationLayout.getLayout());
			layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);

			addressLayout = new GridFormLayoutHelper(2, 6, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			Label addressHeader = new Label("Address Information");
			addressHeader.setStyleName("h2");
			layout.addComponent(addressHeader);
			addressLayout.getLayout().setWidth("100%");
			addressLayout.getLayout().setMargin(false);
			addressLayout.getLayout().setSpacing(false);
			layout.addComponent(addressLayout.getLayout());
			layout.setComponentAlignment(addressLayout.getLayout(),
					Alignment.BOTTOM_CENTER);

			descriptionLayout = new GridFormLayoutHelper(2, 1, "100%", "167px",
					Alignment.MIDDLE_LEFT);
			Label descHeader = new Label("Description");
			descHeader.setStyleName("h2");
			layout.addComponent(descHeader);
			descriptionLayout.getLayout().setWidth("100%");
			descriptionLayout.getLayout().setMargin(false);
			descriptionLayout.getLayout().setSpacing(false);
			layout.addComponent(descriptionLayout.getLayout());
			layout.setComponentAlignment(descriptionLayout.getLayout(),
					Alignment.BOTTOM_CENTER);
			return layout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			informationLayout.addComponent(propertyId.equals("accountname"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_ACCOUNT_NAME), 0,
					0);
			informationLayout.addComponent(propertyId.equals("phoneoffice"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_PHONE_OFFICE), 1,
					0);
			informationLayout
					.addComponent(propertyId.equals("website"), field,
							LocalizationHelper
									.getMessage(AccountI18nEnum.FORM_WEBSITE),
							0, 1);
			informationLayout.addComponent(propertyId.equals("fax"), field,
					LocalizationHelper.getMessage(AccountI18nEnum.FORM_FAX), 1,
					1);
			informationLayout.addComponent(propertyId.equals("numemployees"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_EMPLOYEES), 0, 2);
			informationLayout
					.addComponent(
							propertyId.equals("alternatephone"),
							field,
							LocalizationHelper
									.getMessage(AccountI18nEnum.FORM_OTHER_PHONE),
							1, 2);
			informationLayout.addComponent(propertyId.equals("industry"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_INDUSTRY), 0, 3);
			informationLayout.addComponent(propertyId.equals("email"), field,
					LocalizationHelper.getMessage(AccountI18nEnum.FORM_EMAIL),
					1, 3);
			informationLayout.addComponent(propertyId.equals("type"), field,
					LocalizationHelper.getMessage(AccountI18nEnum.FORM_TYPE),
					0, 4);
			informationLayout.addComponent(propertyId.equals("ownership"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_OWNERSHIP), 1, 4);
			informationLayout
					.addComponent(
							propertyId.equals("assignuser"),
							field,
							LocalizationHelper
									.getMessage(AccountI18nEnum.FORM_ASSIGNED_TO),
							0, 5);
			informationLayout.addComponent(propertyId.equals("annualrevenue"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_ANNUAL_REVENUE),
					1, 5);

			addressLayout.addComponent(propertyId.equals("billingaddress"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_BILLING_ADDRESS),
					0, 0);
			addressLayout.addComponent(propertyId.equals("shippingaddress"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_SHIPPING_ADDRESS),
					1, 0);
			addressLayout.addComponent(propertyId.equals("city"), field,
					LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_BILLING_CITY), 0,
					1);
			addressLayout.addComponent(propertyId.equals("shippingcity"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_SHIPPING_CITY), 1,
					1);
			addressLayout.addComponent(propertyId.equals("state"), field,
					LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_BILLING_STATE), 0,
					2);
			addressLayout.addComponent(propertyId.equals("shippingstate"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_SHIPPING_STATE),
					1, 2);
			addressLayout
					.addComponent(
							propertyId.equals("postalcode"),
							field,
							LocalizationHelper
									.getMessage(AccountI18nEnum.FORM_BILLING_POSTAL_CODE),
							0, 3);
			addressLayout
					.addComponent(
							propertyId.equals("shippingpostalcode"),
							field,
							LocalizationHelper
									.getMessage(AccountI18nEnum.FORM_SHIPPING_POSTAL_CODE),
							1, 3);
			addressLayout.addComponent(propertyId.equals("billingcountry"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_BILLING_COUNTRY),
					0, 4);
			addressLayout.addComponent(propertyId.equals("shippingcountry"),
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_SHIPPING_COUNTRY),
					1, 4);
			addressLayout.addComponent(propertyId.equals("id") && !isReadMode,
					field, LocalizationHelper
							.getMessage(AccountI18nEnum.FORM_COPY_ADDRESS), 0,
					5);

			if (propertyId.equals("description")) {
				field.setSizeUndefined();
				descriptionLayout.addComponent(field, LocalizationHelper
						.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 0, 2,
						UIConstants.DEFAULT_2XCONTROL_WIDTH);
			}
		}
	}
}
