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

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.i18n.OpportunityI18nEnum;
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
class OpportunityHistoryLogWindow extends HistoryLogWindow {
	private static final long serialVersionUID = 1L;

	public static final FieldGroupFomatter opportunityFormatter;

	static {
		opportunityFormatter = new FieldGroupFomatter();

		opportunityFormatter.generateFieldDisplayHandler("opportunityname",
				AppContext.getMessage(OpportunityI18nEnum.FORM_NAME));
		opportunityFormatter.generateFieldDisplayHandler("currencyid",
				AppContext.getMessage(OpportunityI18nEnum.FORM_CURRENCY),
				FieldGroupFomatter.CURRENCY_FIELD);
		opportunityFormatter.generateFieldDisplayHandler("amount",
				AppContext.getMessage(OpportunityI18nEnum.FORM_AMOUNT));
		opportunityFormatter.generateFieldDisplayHandler("salesstage",
				AppContext.getMessage(OpportunityI18nEnum.FORM_SALE_STAGE));
		opportunityFormatter.generateFieldDisplayHandler("probability",
				AppContext.getMessage(OpportunityI18nEnum.FORM_SALE_STAGE));
		opportunityFormatter.generateFieldDisplayHandler("nextstep",
				AppContext.getMessage(OpportunityI18nEnum.FORM_NEXT_STEP));
		opportunityFormatter.generateFieldDisplayHandler("accountid",
				AppContext.getMessage(OpportunityI18nEnum.FORM_ACCOUNT_NAME));
		opportunityFormatter
				.generateFieldDisplayHandler(
						"expectedcloseddate",
						AppContext
								.getMessage(OpportunityI18nEnum.FORM_EXPECTED_CLOSE_DATE),
						FieldGroupFomatter.DATE_FIELD);
		opportunityFormatter.generateFieldDisplayHandler("opportunitytype",
				AppContext.getMessage(OpportunityI18nEnum.FORM_TYPE));
		opportunityFormatter.generateFieldDisplayHandler("source",
				AppContext.getMessage(OpportunityI18nEnum.FORM_LEAD_SOURCE));
		opportunityFormatter.generateFieldDisplayHandler("campaignid",
				AppContext.getMessage(OpportunityI18nEnum.FORM_CAMPAIGN_NAME));
		opportunityFormatter.generateFieldDisplayHandler("assignuser",
				AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE),
				new UserHistoryFieldFormat());
		opportunityFormatter.generateFieldDisplayHandler("description",
				AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION));
	}

	public OpportunityHistoryLogWindow(String module, String type) {
		super(module, type);
	}

	@Override
	protected FieldGroupFomatter buildFormatter() {
		return opportunityFormatter;
	}
}
