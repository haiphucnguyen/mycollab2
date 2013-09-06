/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class AccountHistoryLogWindow extends HistoryLogWindow {
	public AccountHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);

		this.generateFieldDisplayHandler("accountname", "Account Name");
		this.generateFieldDisplayHandler("phoneoffice", LocalizationHelper
				.getMessage(CrmCommonI18nEnum.FORM_PHONE_OFFICE_FIELD));
		this.generateFieldDisplayHandler("website", "Website");
		this.generateFieldDisplayHandler("fax", "Fax");
		this.generateFieldDisplayHandler("numemployees", "Employees");
		this.generateFieldDisplayHandler("alternatephone", "Other Phone");
		this.generateFieldDisplayHandler("industry", "Industry");
		this.generateFieldDisplayHandler("email", "Email");
		this.generateFieldDisplayHandler("type", "Type");
		this.generateFieldDisplayHandler("ownership", "Ownership");
		this.generateFieldDisplayHandler("annualrevenue", "Annual Revenue");
		this.generateFieldDisplayHandler("billingaddress", "Billing Address");
		this.generateFieldDisplayHandler("shippingaddress", "Shipping Address");
		this.generateFieldDisplayHandler("city", "Billing City");
		this.generateFieldDisplayHandler("shippingcity", "Shipping City");
		this.generateFieldDisplayHandler("state", "Billing State");
		this.generateFieldDisplayHandler("shippingstate", "Shipping State");
		this.generateFieldDisplayHandler("postalcode", "Postal Code");
		this.generateFieldDisplayHandler("shippingpostalcode",
				"Shipping Postal Code");
		this.generateFieldDisplayHandler("description", "Description");
	}
}
