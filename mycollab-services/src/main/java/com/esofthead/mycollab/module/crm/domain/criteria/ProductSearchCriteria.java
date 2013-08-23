package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ProductSearchCriteria extends SearchCriteria {
	private StringSearchField productName;

	private StringSearchField accountName;

	private NumberSearchField contactId;

	private NumberSearchField accountId;

	private StringSearchField contactName;

	private NumberSearchField contractId;

	private NumberSearchField productId;

	public StringSearchField getProductName() {
		return productName;
	}

	public void setProductName(StringSearchField productName) {
		this.productName = productName;
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

	public NumberSearchField getAccountId() {
		return accountId;
	}

	public void setAccountId(NumberSearchField accountId) {
		this.accountId = accountId;
	}

	public StringSearchField getContactName() {
		return contactName;
	}

	public void setContactName(StringSearchField contactName) {
		this.contactName = contactName;
	}

	public NumberSearchField getContractId() {
		return contractId;
	}

	public void setContractId(NumberSearchField contractId) {
		this.contractId = contractId;
	}

	public NumberSearchField getProductId() {
		return productId;
	}

	public void setProductId(NumberSearchField productId) {
		this.productId = productId;
	}
}
