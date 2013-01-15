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
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ContactSearchCriteria extends SearchCriteria {
    private StringSearchField contactName;

    private StringSearchField accountName;

    private SetSearchField<String> assignUsers;
    
    private StringSearchField assignUserName;

    private StringSearchField username;

    private NumberSearchField accountId;
    
    private NumberSearchField opportunityId;
    
    private NumberSearchField contractId;
    
    private NumberSearchField caseId;
    
    private NumberSearchField targetListId;
    
    private NumberSearchField saccountid;
    
    private StringSearchField firstname;
    
    private StringSearchField lastname;
    
    private StringSearchField anyEmail;
    
    private StringSearchField anyAddress;
    
    private StringSearchField anyState;
    
    private SetSearchField<String> countries;
    
    private StringSearchField anyPhone;
    
    private StringSearchField anyCity;
    
    private StringSearchField anyPostalCode;
    
    private SetSearchField<String> leadSources;
    
    private NumberSearchField id;
    

	public StringSearchField getContactName() {
		return contactName;
	}

	public void setContactName(StringSearchField contactName) {
		this.contactName = contactName;
	}

	public StringSearchField getAccountName() {
		return accountName;
	}

	public void setAccountName(StringSearchField accountName) {
		this.accountName = accountName;
	}

	public void setAssignUsers(SetSearchField<String> assignUsers) {
		this.assignUsers = assignUsers;
	}

	public SetSearchField<String> getAssignUsers() {
		return assignUsers;
	}

	public StringSearchField getUsername() {
		return username;
	}

	public void setUsername(StringSearchField username) {
		this.username = username;
	}

	public NumberSearchField getAccountId() {
		return accountId;
	}

	public void setAccountId(NumberSearchField accountId) {
		this.accountId = accountId;
	}

	public NumberSearchField getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(NumberSearchField opportunityId) {
		this.opportunityId = opportunityId;
	}

	public NumberSearchField getContractId() {
		return contractId;
	}

	public void setContractId(NumberSearchField contractId) {
		this.contractId = contractId;
	}

	public NumberSearchField getCaseId() {
		return caseId;
	}

	public void setCaseId(NumberSearchField caseId) {
		this.caseId = caseId;
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

	public StringSearchField getAssignUserName() {
		return assignUserName;
	}

	public void setAssignUserName(StringSearchField assignUserName) {
		this.assignUserName = assignUserName;
	}

	public StringSearchField getFirstname() {
		return firstname;
	}

	public void setFirstname(StringSearchField firstname) {
		this.firstname = firstname;
	}

	public StringSearchField getLastname() {
		return lastname;
	}

	public void setLastname(StringSearchField lastname) {
		this.lastname = lastname;
	}

	public StringSearchField getAnyEmail() {
		return anyEmail;
	}

	public void setAnyEmail(StringSearchField anyEmail) {
		this.anyEmail = anyEmail;
	}

	public StringSearchField getAnyAddress() {
		return anyAddress;
	}

	public void setAnyAddress(StringSearchField anyAddress) {
		this.anyAddress = anyAddress;
	}

	public StringSearchField getAnyState() {
		return anyState;
	}

	public void setAnyState(StringSearchField anyState) {
		this.anyState = anyState;
	}

	public void setCountries(SetSearchField<String> countries) {
		this.countries = countries;
	}

	public SetSearchField<String> getCountries() {
		return countries;
	}

	public StringSearchField getAnyPhone() {
		return anyPhone;
	}

	public void setAnyPhone(StringSearchField anyPhone) {
		this.anyPhone = anyPhone;
	}

	public StringSearchField getAnyCity() {
		return anyCity;
	}

	public void setAnyCity(StringSearchField anyCity) {
		this.anyCity = anyCity;
	}

	public StringSearchField getAnyPostalCode() {
		return anyPostalCode;
	}

	public void setAnyPostalCode(StringSearchField anyPostalCode) {
		this.anyPostalCode = anyPostalCode;
	}

	public void setLeadSources(SetSearchField<String> leadSources) {
		this.leadSources = leadSources;
	}

	public SetSearchField<String> getLeadSources() {
		return leadSources;
	}

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getId() {
		return id;
	}
}
