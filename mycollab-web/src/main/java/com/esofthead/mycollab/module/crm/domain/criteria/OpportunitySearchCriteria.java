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

public class OpportunitySearchCriteria extends SearchCriteria {
    private StringSearchField opportunityName;

    private StringSearchField accountName;

    private StringSearchField campaignName;

    private StringSearchField assignUser;

    private StringSearchField assignUserName;

    private NumberSearchField accountId;
    
    private NumberSearchField contactId;
    
    private NumberSearchField quoteId;
    
    private NumberSearchField saccountid;

	public StringSearchField getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(StringSearchField opportunityName) {
		this.opportunityName = opportunityName;
	}

	public StringSearchField getAccountName() {
		return accountName;
	}

	public void setAccountName(StringSearchField accountName) {
		this.accountName = accountName;
	}

	public StringSearchField getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(StringSearchField campaignName) {
		this.campaignName = campaignName;
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

	public NumberSearchField getAccountId() {
		return accountId;
	}

	public void setAccountId(NumberSearchField accountId) {
		this.accountId = accountId;
	}

	public NumberSearchField getContactId() {
		return contactId;
	}

	public void setContactId(NumberSearchField contactId) {
		this.contactId = contactId;
	}

	public NumberSearchField getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(NumberSearchField quoteId) {
		this.quoteId = quoteId;
	}

	public NumberSearchField getSaccountid() {
		return saccountid;
	}

	public void setSaccountid(NumberSearchField saccountid) {
		this.saccountid = saccountid;
	}
}
