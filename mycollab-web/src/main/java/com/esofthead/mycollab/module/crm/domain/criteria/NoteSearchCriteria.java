package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class NoteSearchCriteria extends SearchCriteria {
	private StringSearchField subject;

    private StringSearchField contact;

    private NumberSearchField contactId;
    
    private NumberSearchField contractId;

    private StringSearchField assignUser;

    private NumberSearchField accountId;

    private NumberSearchField campaignId;

    private NumberSearchField targetId;

    private NumberSearchField leadId;

    private NumberSearchField opportunityId;

    private NumberSearchField quoteId;

    private NumberSearchField productId;

    private NumberSearchField caseId;

    private StringSearchField username;

    private NumberSearchField type;
    
    private NumberSearchField saccountid;

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

	public NumberSearchField getContractId() {
		return contractId;
	}

	public void setContractId(NumberSearchField contractId) {
		this.contractId = contractId;
	}

	public StringSearchField getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(StringSearchField assignUser) {
		this.assignUser = assignUser;
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

	public StringSearchField getUsername() {
		return username;
	}

	public void setUsername(StringSearchField username) {
		this.username = username;
	}

	public NumberSearchField getType() {
		return type;
	}

	public void setType(NumberSearchField type) {
		this.type = type;
	}

	public NumberSearchField getSaccountid() {
		return saccountid;
	}

	public void setSaccountid(NumberSearchField saccountid) {
		this.saccountid = saccountid;
	}
}
