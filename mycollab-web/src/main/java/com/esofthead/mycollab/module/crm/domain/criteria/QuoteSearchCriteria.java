package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class QuoteSearchCriteria extends SearchCriteria {
    private StringSearchField subject;

    private NumberSearchField opportunityId;

    private NumberSearchField accountId;

    private StringSearchField assignUserName;
    
    private StringSearchField assignUser;

    private StringSearchField billingAccountName;

    private StringSearchField billingContactName;

    private StringSearchField shippingAccountName;

    private StringSearchField shippingContactName;
    
    private NumberSearchField contractId;
    
    private NumberSearchField contactId;
    
    private NumberSearchField saccountid;

	public StringSearchField getSubject() {
		return subject;
	}

	public void setSubject(StringSearchField subject) {
		this.subject = subject;
	}

	public NumberSearchField getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(NumberSearchField opportunityId) {
		this.opportunityId = opportunityId;
	}

	public NumberSearchField getAccountId() {
		return accountId;
	}

	public void setAccountId(NumberSearchField accountId) {
		this.accountId = accountId;
	}

	public StringSearchField getAssignUserName() {
		return assignUserName;
	}

	public void setAssignUserName(StringSearchField assignUserName) {
		this.assignUserName = assignUserName;
	}

	public StringSearchField getBillingAccountName() {
		return billingAccountName;
	}

	public void setBillingAccountName(StringSearchField billingAccountName) {
		this.billingAccountName = billingAccountName;
	}

	public StringSearchField getBillingContactName() {
		return billingContactName;
	}

	public void setBillingContactName(StringSearchField billingContactName) {
		this.billingContactName = billingContactName;
	}

	public StringSearchField getShippingAccountName() {
		return shippingAccountName;
	}

	public void setShippingAccountName(StringSearchField shippingAccountName) {
		this.shippingAccountName = shippingAccountName;
	}

	public StringSearchField getShippingContactName() {
		return shippingContactName;
	}

	public void setShippingContactName(StringSearchField shippingContactName) {
		this.shippingContactName = shippingContactName;
	}

	public NumberSearchField getContractId() {
		return contractId;
	}

	public void setContractId(NumberSearchField contractId) {
		this.contractId = contractId;
	}

	public NumberSearchField getContactId() {
		return contactId;
	}

	public void setContactId(NumberSearchField contactId) {
		this.contactId = contactId;
	}

	public NumberSearchField getSaccountid() {
		return saccountid;
	}

	public void setSaccountid(NumberSearchField saccountid) {
		this.saccountid = saccountid;
	}

	public StringSearchField getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(StringSearchField assignUser) {
		this.assignUser = assignUser;
	}
}
