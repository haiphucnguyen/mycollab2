/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.BitSearchField;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;

/**
 * 
 * @author haiphucnguyen
 */
public class MeetingSearchCriteria extends SearchCriteria {

	private SetSearchField<String> assignUsers;
	private NumberSearchField id;
	private BitSearchField isClosed;
	private DateSearchField startDate;
	private DateSearchField endDate;

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

	public void setIsClosed(BitSearchField isClosed) {
		this.isClosed = isClosed;
	}

	public BitSearchField getIsClosed() {
		return isClosed;
	}

	public DateSearchField getStartDate() {
		return startDate;
	}

	public void setStartDate(DateSearchField startDate) {
		this.startDate = startDate;
	}

	public DateSearchField getEndDate() {
		return endDate;
	}

	public void setEndDate(DateSearchField endDate) {
		this.endDate = endDate;
	}
}
