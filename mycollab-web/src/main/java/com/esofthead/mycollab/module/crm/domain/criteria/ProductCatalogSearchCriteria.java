package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ProductCatalogSearchCriteria extends SearchCriteria {
    private StringSearchField productName;

    private StringSearchField mftNumber;
    
    private NumberSearchField saccountid;

	public StringSearchField getProductName() {
		return productName;
	}

	public void setProductName(StringSearchField productName) {
		this.productName = productName;
	}

	public StringSearchField getMftNumber() {
		return mftNumber;
	}

	public void setMftNumber(StringSearchField mftNumber) {
		this.mftNumber = mftNumber;
	}

	public NumberSearchField getSaccountid() {
		return saccountid;
	}

	public void setSaccountid(NumberSearchField saccountid) {
		this.saccountid = saccountid;
	}
}
