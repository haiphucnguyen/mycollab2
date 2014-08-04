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
package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.i18n.ContactI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.module.user.ui.components.UserHistoryFieldFormat;
import com.esofthead.mycollab.utils.FieldGroupFomatter;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
class ContactHistoryLogWindow extends HistoryLogWindow {
	private static final long serialVersionUID = 1L;

	public static final FieldGroupFomatter contactFormatter;

	static {
		contactFormatter = new FieldGroupFomatter();

		contactFormatter.generateFieldDisplayHandler("firstname",
				AppContext.getMessage(ContactI18nEnum.FORM_FIRSTNAME));
		contactFormatter.generateFieldDisplayHandler("lastname",
				AppContext.getMessage(ContactI18nEnum.FORM_LASTNAME));
		contactFormatter.generateFieldDisplayHandler("accountId",
				AppContext.getMessage(ContactI18nEnum.FORM_ACCOUNTS));
		contactFormatter.generateFieldDisplayHandler("title",
				AppContext.getMessage(ContactI18nEnum.FORM_TITLE));
		contactFormatter.generateFieldDisplayHandler("department",
				AppContext.getMessage(ContactI18nEnum.FORM_DEPARTMENT));
		contactFormatter.generateFieldDisplayHandler("email",
				AppContext.getMessage(ContactI18nEnum.FORM_EMAIL));
		contactFormatter.generateFieldDisplayHandler("assistant",
				AppContext.getMessage(ContactI18nEnum.FORM_ASSISTANT));
		contactFormatter.generateFieldDisplayHandler("assistantphone",
				AppContext.getMessage(ContactI18nEnum.FORM_ASSISTANT_PHONE));
		contactFormatter.generateFieldDisplayHandler("leadsource",
				AppContext.getMessage(ContactI18nEnum.FORM_LEAD_SOURCE));
		contactFormatter.generateFieldDisplayHandler("officephone",
				AppContext.getMessage(ContactI18nEnum.FORM_OFFICE_PHONE));
		contactFormatter.generateFieldDisplayHandler("mobile",
				AppContext.getMessage(ContactI18nEnum.FORM_MOBILE));
		contactFormatter.generateFieldDisplayHandler("homephone",
				AppContext.getMessage(ContactI18nEnum.FORM_HOME_PHONE));
		contactFormatter.generateFieldDisplayHandler("otherphone",
				AppContext.getMessage(ContactI18nEnum.FORM_OTHER_PHONE));
		contactFormatter.generateFieldDisplayHandler("birthday",
				AppContext.getMessage(ContactI18nEnum.FORM_BIRTHDAY),
				FieldGroupFomatter.DATE_FIELD);
		contactFormatter.generateFieldDisplayHandler("iscallable",
				AppContext.getMessage(ContactI18nEnum.FORM_IS_CALLABLE));
		contactFormatter.generateFieldDisplayHandler("assignuser",
				AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE),
				new UserHistoryFieldFormat());
		contactFormatter.generateFieldDisplayHandler("primaddress",
				AppContext.getMessage(ContactI18nEnum.FORM_PRIMARY_ADDRESS));
		contactFormatter.generateFieldDisplayHandler("primcity",
				AppContext.getMessage(ContactI18nEnum.FORM_PRIMARY_CITY));
		contactFormatter.generateFieldDisplayHandler("primstate",
				AppContext.getMessage(ContactI18nEnum.FORM_PRIMARY_STATE));
		contactFormatter
				.generateFieldDisplayHandler("primpostalcode", AppContext
						.getMessage(ContactI18nEnum.FORM_PRIMARY_POSTAL_CODE));
		contactFormatter.generateFieldDisplayHandler("primcountry",
				AppContext.getMessage(ContactI18nEnum.FORM_PRIMARY_COUNTRY));
		contactFormatter.generateFieldDisplayHandler("otheraddress",
				AppContext.getMessage(ContactI18nEnum.FORM_OTHER_ADDRESS));
		contactFormatter.generateFieldDisplayHandler("othercity",
				AppContext.getMessage(ContactI18nEnum.FORM_OTHER_CITY));
		contactFormatter.generateFieldDisplayHandler("otherstate",
				AppContext.getMessage(ContactI18nEnum.FORM_OTHER_STATE));
		contactFormatter.generateFieldDisplayHandler("otherpostalcode",
				AppContext.getMessage(ContactI18nEnum.FORM_OTHER_POSTAL_CODE));
		contactFormatter.generateFieldDisplayHandler("othercountry",
				AppContext.getMessage(ContactI18nEnum.FORM_OTHER_COUNTRY));
		contactFormatter.generateFieldDisplayHandler("description",
				AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION));
	}

	public ContactHistoryLogWindow(String module, String type) {
		super(module, type);
	}

	@Override
	protected FieldGroupFomatter buildFormatter() {
		return contactFormatter;
	}

}
