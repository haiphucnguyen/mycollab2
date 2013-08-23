/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.BitSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;

/**
 * 
 * @author haiphucnguyen
 */
public class CallSearchCriteria extends SearchCriteria {
	private SetSearchField<String> assignUsers;
	private NumberSearchField id;
	private BitSearchField isClosed;

	public SetSearchField<String> getAssignUsers() {
		return assignUsers;
	}

	public void setAssignUsers(SetSearchField<String> assignUsers) {
		this.assignUsers = assignUsers;
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
