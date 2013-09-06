package com.esofthead.mycollab.module.crm.view.account;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.FieldMapperDef;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.ui.components.EntityImportWindow;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class AccountImportWindow extends EntityImportWindow<Account> {
	public AccountImportWindow() {
		super(false, "Import Account Window", AppContext
				.getSpringBean(AccountService.class), Account.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected List<FieldMapperDef> constructCSVFieldMapper() {
		FieldMapperDef[] fields = {
				new FieldMapperDef("accountname", "Account Name"),
				new FieldMapperDef("website", "Website"),
				new FieldMapperDef(
						"phoneoffice",
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.FORM_PHONE_OFFICE_FIELD)),
				new FieldMapperDef("fax", "Fax"),
				new FieldMapperDef("alternatephone", "Alternate Phone"),
				new FieldMapperDef("annualrevenue", "Annual Revenue"),
				new FieldMapperDef("billingaddress", "Billing Address"),
				new FieldMapperDef("city", "City"),
				new FieldMapperDef("postalcode", "Postal Code"),
				new FieldMapperDef("state", "State"),
				new FieldMapperDef("email", "Email"),
				new FieldMapperDef("ownership", "Ownership"),
				new FieldMapperDef("shippingaddress", "Shipping Address"),
				new FieldMapperDef("shippingcity", "Shipping City"),
				new FieldMapperDef("shippingpostalcode", "Shipping Postal Code"),
				new FieldMapperDef("shippingstate", "Shipping State"),
				new FieldMapperDef("numemployees", "Number Employees"),
				new FieldMapperDef("assignuser",
						LocalizationHelper
								.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD)),
				new FieldMapperDef("type", "Type"),
				new FieldMapperDef("industry", "Industry"),
				new FieldMapperDef("billingcountry", "Billing Country"),
				new FieldMapperDef("shippingcountry", "Shipping Country"),
				new FieldMapperDef("description", "Description") };
		return Arrays.asList(fields);
	}

	@Override
	protected void reloadWhenBackToListView() {
		EventBus.getInstance().fireEvent(
				new AccountEvent.GotoList(AccountListView.class,
						new AccountSearchCriteria()));
	}

}