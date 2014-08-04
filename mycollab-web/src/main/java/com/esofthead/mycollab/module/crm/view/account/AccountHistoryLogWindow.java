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

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.i18n.AccountI18nEnum;
import com.esofthead.mycollab.module.crm.i18n.OptionI18nEnum.AccountType;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.module.user.ui.components.UserHistoryFieldFormat;
import com.esofthead.mycollab.utils.FieldGroupFomatter;
import com.esofthead.mycollab.utils.FieldGroupFomatter.I18nHistoryFieldFormat;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
class AccountHistoryLogWindow extends HistoryLogWindow {
	private static final long serialVersionUID = 1L;

	public static final FieldGroupFomatter accountFormatter;

	static {
		accountFormatter = new FieldGroupFomatter();

		accountFormatter.generateFieldDisplayHandler("accountname",
				AppContext.getMessage(AccountI18nEnum.FORM_ACCOUNT_NAME));

		accountFormatter.generateFieldDisplayHandler("assignuser",
				AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE),
				new UserHistoryFieldFormat());
		accountFormatter.generateFieldDisplayHandler("phoneoffice",
				AppContext.getMessage(AccountI18nEnum.FORM_OFFICE_PHONE));
		accountFormatter.generateFieldDisplayHandler("website",
				AppContext.getMessage(AccountI18nEnum.FORM_WEBSITE));
		accountFormatter.generateFieldDisplayHandler("fax",
				AppContext.getMessage(AccountI18nEnum.FORM_FAX));
		accountFormatter.generateFieldDisplayHandler("numemployees",
				AppContext.getMessage(AccountI18nEnum.FORM_EMPLOYEES));
		accountFormatter.generateFieldDisplayHandler("alternatephone",
				AppContext.getMessage(AccountI18nEnum.FORM_OTHER_PHONE));
		accountFormatter.generateFieldDisplayHandler("industry",
				AppContext.getMessage(AccountI18nEnum.FORM_INDUSTRY));
		accountFormatter.generateFieldDisplayHandler("email",
				AppContext.getMessage(AccountI18nEnum.FORM_EMAIL));
		accountFormatter.generateFieldDisplayHandler("type",
				AppContext.getMessage(AccountI18nEnum.FORM_TYPE),
				new I18nHistoryFieldFormat(AccountType.class));
		accountFormatter.generateFieldDisplayHandler("ownership",
				AppContext.getMessage(AccountI18nEnum.FORM_OWNERSHIP));
		accountFormatter.generateFieldDisplayHandler("annualrevenue",
				AppContext.getMessage(AccountI18nEnum.FORM_ANNUAL_REVENUE));
		accountFormatter.generateFieldDisplayHandler("billingaddress",
				AppContext.getMessage(AccountI18nEnum.FORM_BILLING_ADDRESS));
		accountFormatter.generateFieldDisplayHandler("shippingaddress",
				AppContext.getMessage(AccountI18nEnum.FORM_SHIPPING_ADDRESS));
		accountFormatter.generateFieldDisplayHandler("city",
				AppContext.getMessage(AccountI18nEnum.FORM_BILLING_CITY));
		accountFormatter.generateFieldDisplayHandler("shippingcity",
				AppContext.getMessage(AccountI18nEnum.FORM_SHIPPING_CITY));
		accountFormatter.generateFieldDisplayHandler("state",
				AppContext.getMessage(AccountI18nEnum.FORM_BILLING_STATE));
		accountFormatter.generateFieldDisplayHandler("shippingstate",
				AppContext.getMessage(AccountI18nEnum.FORM_SHIPPING_STATE));
		accountFormatter
				.generateFieldDisplayHandler("postalcode", AppContext
						.getMessage(AccountI18nEnum.FORM_BILLING_POSTAL_CODE));
		accountFormatter.generateFieldDisplayHandler("shippingpostalcode",
				AppContext
						.getMessage(AccountI18nEnum.FORM_SHIPPING_POSTAL_CODE));
		accountFormatter.generateFieldDisplayHandler("description",
				AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION));
	}

	public AccountHistoryLogWindow(String module, String type, int typeid) {
		super(module, type);
	}

	@Override
	protected FieldGroupFomatter buildFormatter() {
		return accountFormatter;
	}
}
