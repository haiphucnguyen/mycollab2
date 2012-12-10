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

public class AccountSearchCriteria extends SearchCriteria {
    private StringSearchField accountname;

    private StringSearchField assignUser;
    
    private StringSearchField assignUserName;
    
    private StringSearchField city;
    
    private StringSearchField website;
    
    private SetSearchField<String> types;
    
    private SetSearchField<String> industries;
    
    private SetSearchField<String> assignUsers;
    
    private StringSearchField anyPhone;
    
    private NumberSearchField saccountid;

	public StringSearchField getAccountname() {
		return accountname;
	}

	public void setAccountname(StringSearchField accountname) {
		this.accountname = accountname;
	}

	public StringSearchField getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(StringSearchField assignUser) {
		this.assignUser = assignUser;
	}

	public StringSearchField getCity() {
		return city;
	}

	public void setCity(StringSearchField city) {
		this.city = city;
	}

	public StringSearchField getWebsite() {
		return website;
	}

	public void setWebsite(StringSearchField website) {
		this.website = website;
	}

	public SetSearchField<String> getTypes() {
		return types;
	}

	public void setTypes(SetSearchField<String> types) {
		this.types = types;
	}

	public SetSearchField<String> getIndustries() {
		return industries;
	}

	public void setIndustries(SetSearchField<String> industries) {
		this.industries = industries;
	}

	public SetSearchField<String> getAssignUsers() {
		return assignUsers;
	}

	public void setAssignUsers(SetSearchField<String> assignUsers) {
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

	public StringSearchField getAnyPhone() {
		return anyPhone;
	}

	public void setAnyPhone(StringSearchField anyPhone) {
		this.anyPhone = anyPhone;
	}
}
