/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.BitSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class TodoSearchCriteria extends SearchCriteria {

    private StringSearchField subject;
    private StringSearchField contact;
    private NumberSearchField contactId;
    private NumberSearchField accountId;
    private NumberSearchField campaignId;
    private NumberSearchField targetId;
    private NumberSearchField leadId;
    private NumberSearchField opportunityId;
    private NumberSearchField quoteId;
    private NumberSearchField productId;
    private NumberSearchField caseId;
    private StringSearchField assignUser;
    private StringSearchField type;
    private NumberSearchField saccountid;
    private StringSearchField status;
    private NumberSearchField id;
    
    private BitSearchField isClosed;

    public StringSearchField getSubject() {
        return subject;
    }

    public void setSubject(StringSearchField subject) {
        this.subject = subject;
    }

    public StringSearchField getContact() {
        return contact;
    }

    public void setContact(StringSearchField contact) {
        this.contact = contact;
    }

    public NumberSearchField getContactId() {
        return contactId;
    }

    public void setContactId(NumberSearchField contactId) {
        this.contactId = contactId;
    }

    public NumberSearchField getAccountId() {
        return accountId;
    }

    public void setAccountId(NumberSearchField accountId) {
        this.accountId = accountId;
    }

    public NumberSearchField getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(NumberSearchField campaignId) {
        this.campaignId = campaignId;
    }

    public NumberSearchField getTargetId() {
        return targetId;
    }

    public void setTargetId(NumberSearchField targetId) {
        this.targetId = targetId;
    }

    public NumberSearchField getLeadId() {
        return leadId;
    }

    public void setLeadId(NumberSearchField leadId) {
        this.leadId = leadId;
    }

    public NumberSearchField getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(NumberSearchField opportunityId) {
        this.opportunityId = opportunityId;
    }

    public NumberSearchField getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(NumberSearchField quoteId) {
        this.quoteId = quoteId;
    }

    public NumberSearchField getProductId() {
        return productId;
    }

    public void setProductId(NumberSearchField productId) {
        this.productId = productId;
    }

    public NumberSearchField getCaseId() {
        return caseId;
    }

    public void setCaseId(NumberSearchField caseId) {
        this.caseId = caseId;
    }

    public StringSearchField getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(StringSearchField assignUser) {
        this.assignUser = assignUser;
    }

    public StringSearchField getType() {
        return type;
    }

    public void setType(StringSearchField type) {
        this.type = type;
    }

    public NumberSearchField getSaccountid() {
        return saccountid;
    }

    public void setSaccountid(NumberSearchField saccountid) {
        this.saccountid = saccountid;
    }

    public StringSearchField getStatus() {
        return status;
    }

    public void setStatus(StringSearchField status) {
        this.status = status;
    }

    public void setId(NumberSearchField id) {
        this.id = id;
    }

    public NumberSearchField getId() {
        return id;
    }

    public BitSearchField getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(BitSearchField isClosed) {
        this.isClosed = isClosed;
    }
    
    
}
