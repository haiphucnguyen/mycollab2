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

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.UserLinkViewField;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class CampaignReadFormFieldFactory extends
		AbstractBeanFieldGroupViewFieldFactory<SimpleCampaign> {
	private static final long serialVersionUID = 1L;

	public CampaignReadFormFieldFactory(GenericBeanForm<SimpleCampaign> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		SimpleCampaign campaign = attachForm.getBean();

		if (propertyId.equals("assignuser")) {
			return new UserLinkViewField(campaign.getAssignuser(),
					campaign.getAssignUserAvatarId(),
					campaign.getAssignUserFullName());
		} else if (propertyId.equals("startdate")) {
			return new FormViewField(AppContext.formatDate(campaign
					.getStartdate()));
		} else if (propertyId.equals("enddate")) {
			return new FormViewField(AppContext.formatDate(campaign
					.getEnddate()));
		} else if (propertyId.equals("currencyid")) {
			if (campaign.getCurrency() != null) {
				return new FormViewField(campaign.getCurrency().getShortname());
			} else {
				return new FormViewField("");
			}
		}

		return null;
	}

}
