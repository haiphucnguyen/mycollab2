/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class LeadSearchCriteria extends SearchCriteria {
	private StringSearchField campaignName;

	private StringSearchField referredBy;

	private StringSearchField leadName;

	private StringSearchField accountName;

	private StringSearchField assignUser;

	private StringSearchField assignUserName;

	private NumberSearchField campaignId;

	private NumberSearchField opportunityId;

	private NumberSearchField targetListId;

	private NumberSearchField saccountid;

	public StringSearchField getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(StringSearchField campaignName) {
		this.campaignName = campaignName;
	}

	public StringSearchField getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(StringSearchField referredBy) {
		this.referredBy = referredBy;
	}

	public StringSearchField getLeadName() {
		return leadName;
	}

	public void setLeadName(StringSearchField leadName) {
		this.leadName = leadName;
	}

	public StringSearchField getAccountName() {
		return accountName;
	}

	public void setAccountName(StringSearchField accountName) {
		this.accountName = accountName;
	}

	public StringSearchField getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(StringSearchField assignUser) {
		this.assignUser = assignUser;
	}

	public StringSearchField getAssignUserName() {
		return assignUserName;
	}

	public void setAssignUserName(StringSearchField assignUserName) {
		this.assignUserName = assignUserName;
	}

	public NumberSearchField getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(NumberSearchField campaignId) {
		this.campaignId = campaignId;
	}

	public NumberSearchField getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(NumberSearchField opportunityId) {
		this.opportunityId = opportunityId;
	}

	public NumberSearchField getTargetListId() {
		return targetListId;
	}

	public void setTargetListId(NumberSearchField targetListId) {
		this.targetListId = targetListId;
	}

	public NumberSearchField getSaccountid() {
		return saccountid;
	}

	public void setSaccountid(NumberSearchField saccountid) {
		this.saccountid = saccountid;
	}
	
}
