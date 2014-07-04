/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.mobile.module.crm.ui.AbstractRelatedListView;
import com.esofthead.mycollab.mobile.module.crm.view.campaign.CampaignListDisplay;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class LeadRelatedCampaignView extends
		AbstractRelatedListView<SimpleCampaign, CampaignSearchCriteria> {
	private static final long serialVersionUID = 3836477709565175561L;
	private SimpleLead lead;

	public LeadRelatedCampaignView() {
		super();

		setCaption("Related Campaigns");
		this.itemList = new CampaignListDisplay();
		this.setContent(this.itemList);
	}

	private void loadCampaigns() {
		final CampaignSearchCriteria searchCriteria = new CampaignSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		searchCriteria.setLeadId(new NumberSearchField(SearchField.AND,
				this.lead.getId()));
		this.itemList.setSearchCriteria(searchCriteria);
	}

	public void displayCampaign(SimpleLead lead) {
		this.lead = lead;
		loadCampaigns();
	}

	@Override
	public void refresh() {
		loadCampaigns();
	}

}
