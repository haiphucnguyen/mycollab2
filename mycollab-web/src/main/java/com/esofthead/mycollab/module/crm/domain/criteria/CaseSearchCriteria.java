package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class CaseSearchCriteria extends SearchCriteria {
	private StringSearchField subject;

	private StringSearchField assignUser;
	
	private StringSearchField assignUserName;

	private NumberSearchField accountId;

	private StringSearchField accountName;

	private NumberSearchField contactId;

	private SetSearchField statuses;
	
	private SetSearchField priorities;
	
	private SetSearchField assignUsers;

	private NumberSearchField saccountid;

	public StringSearchField getSubject() {
		return subject;
	}

	public void setSubject(StringSearchField subject) {
		this.subject = subject;
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

	public StringSearchField getAccountName() {
		return accountName;
	}

	public void setAccountName(StringSearchField accountName) {
		this.accountName = accountName;
	}

	public NumberSearchField getContactId() {
		return contactId;
	}

	public void setContactId(NumberSearchField contactId) {
		this.contactId = contactId;
	}

	public SetSearchField getStatuses() {
		return statuses;
	}

	public void setStatuses(SetSearchField statuses) {
		this.statuses = statuses;
	}

	public SetSearchField getPriorities() {
		return priorities;
	}

	public void setPriorities(SetSearchField priorities) {
		this.priorities = priorities;
	}

	public SetSearchField getAssignUsers() {
		return assignUsers;
	}

	public void setAssignUsers(SetSearchField assignUsers) {
		this.assignUsers = assignUsers;
	}

	public NumberSearchField getSaccountid() {
		return saccountid;
	}

	public void setSaccountid(NumberSearchField saccountid) {
		this.saccountid = saccountid;
	}

	public StringSearchField getAssignUserName() {
		return assignUserName;
	}

	public void setAssignUserName(StringSearchField assignUserName) {
		this.assignUserName = assignUserName;
	}
}
