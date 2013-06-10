package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.localization.AccountI18nEnum;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MassUpdateAccountWindow extends MassUpdateWindow<Account> {
	private static final long serialVersionUID = 1L;

	private Account account;
	private final EditForm updateForm;
	private ReadViewLayout accountAddLayout;
	private VerticalLayout layout;

	public MassUpdateAccountWindow(String title, AccountListPresenter presenter) {
		super(title, presenter);
		this.setWidth("1000px");

		accountAddLayout = new ReadViewLayout(new ThemeResource(
				"icons/18/account.png"));

		account = new Account();

		layout = getLayout();

		updateForm = new EditForm();

		updateForm.setItemDataSource(new BeanItem<Account>(account));

		accountAddLayout.addComponent(updateForm);

		this.addComponent(accountAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			setFormLayoutFactory(new MassUpdateAccountFormLayoutFactory());
			setFormFieldFactory(new AccountEditFormFieldFactory(account));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateAccountFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;
			private GridFormLayoutHelper addressLayout;

			@Override
			public Layout getLayout() {
				VerticalLayout formLayout = new VerticalLayout();

				Label organizationHeader = new Label("Account Information");
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

				addressLayout = new GridFormLayoutHelper(2, 6, "100%", "167px",
						Alignment.MIDDLE_LEFT);
				Label addressHeader = new Label("Address Information");
				addressHeader.setStyleName("h2");
				formLayout.addComponent(addressHeader);
				addressLayout.getLayout().setWidth("100%");
				addressLayout.getLayout().setMargin(false);
				addressLayout.getLayout().setSpacing(false);
				addressLayout.getLayout().addStyleName("colored-gridlayout");
				formLayout.addComponent(addressLayout.getLayout());

				formLayout.addComponent(layout);

				return formLayout;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				informationLayout.addComponent(propertyId.equals("industry"),
						field, LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_INDUSTRY), 0,
						1);

				informationLayout.addComponent(propertyId.equals("type"),
						field, LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_TYPE), 1, 1);
				informationLayout.addComponent(propertyId.equals("ownership"),
						field, LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_OWNERSHIP), 0,
						2);

				informationLayout.addComponent(propertyId.equals("assignuser"),
						field, LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_ASSIGNED_TO),
						1, 2);

				addressLayout.addComponent(propertyId.equals("city"), field,
						LocalizationHelper
								.getMessage(AccountI18nEnum.FORM_BILLING_CITY),
						0, 1);

				addressLayout
						.addComponent(
								propertyId.equals("shippingcity"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_CITY),
								1, 1);

				addressLayout
						.addComponent(
								propertyId.equals("state"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_STATE),
								0, 2);

				addressLayout
						.addComponent(
								propertyId.equals("postalcode"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_POSTAL_CODE),
								1, 2);

				addressLayout
						.addComponent(
								propertyId.equals("billingcountry"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_BILLING_COUNTRY),
								0, 3);
				addressLayout
						.addComponent(
								propertyId.equals("shippingcountry"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_COUNTRY),
								1, 3);

				addressLayout
						.addComponent(
								propertyId.equals("shippingstate"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_STATE),
								0, 4);
				addressLayout
						.addComponent(
								propertyId.equals("shippingpostalcode"),
								field,
								LocalizationHelper
										.getMessage(AccountI18nEnum.FORM_SHIPPING_POSTAL_CODE),
								1, 4);
			}
		}
	}

	@Override
	protected Account getItem() {
		return account;
	}
}
