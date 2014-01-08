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

package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
class CampaignHistoryLogWindow extends HistoryLogWindow {
	private static final long serialVersionUID = 1L;

	public CampaignHistoryLogWindow(String module, String type) {
		super(module, type);

		this.generateFieldDisplayHandler("campaignname", "Name");
		this.generateFieldDisplayHandler("startdate", "Start Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("enddate", "End Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("type", "Type");
		this.generateFieldDisplayHandler("currencyid", "Currency",
				HistoryLogComponent.CURRENCY_FIELD);
		this.generateFieldDisplayHandler("budget", "Budget");
		this.generateFieldDisplayHandler("expectedcost", "Expected Cost");
		this.generateFieldDisplayHandler("actualcost", "Actual Cost");
		this.generateFieldDisplayHandler("expectedrevenue", "Expected Revenue");
		this.generateFieldDisplayHandler("assignuser", LocalizationHelper
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		this.generateFieldDisplayHandler("description", "Description");
	}
}
