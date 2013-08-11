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
package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.dao.AccountLeadMapper;
import com.esofthead.mycollab.module.crm.dao.AccountMapper;
import com.esofthead.mycollab.module.crm.dao.AccountMapperExt;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.AccountLead;
import com.esofthead.mycollab.module.crm.domain.AccountLeadExample;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.CRM, type = CrmTypeConstants.ACCOUNT, nameField = "accountname")
@Auditable(module = ModuleNameConstants.CRM, type = CrmTypeConstants.ACCOUNT)
public class AccountServiceImpl extends
		DefaultService<Integer, Account, AccountSearchCriteria> implements
		AccountService {

	@Autowired
	protected AccountMapper accountMapper;
	@Autowired
	protected AccountMapperExt accountMapperExt;

	@Autowired
	protected AccountLeadMapper accountLeadMapper;

	@Override
	public ICrudGenericDAO<Integer, Account> getCrudMapper() {
		return accountMapper;
	}

	@Override
	public ISearchableDAO<AccountSearchCriteria> getSearchMapper() {
		return accountMapperExt;
	}

	@Override
	public SimpleAccount findById(int accountId) {
		return accountMapperExt.findAccountById(accountId);
	}

	@Override
	public void saveAccountLeadRelationship(List<AccountLead> associateLeads) {
		for (AccountLead associateLead : associateLeads) {
			AccountLeadExample ex = new AccountLeadExample();
			ex.createCriteria()
					.andAccountidEqualTo(associateLead.getAccountid())
					.andLeadidEqualTo(associateLead.getLeadid());
			if (accountLeadMapper.countByExample(ex) == 0) {
				accountLeadMapper.insert(associateLead);
			}
		}
	}

	@Override
	public void removeAccountLeadRelationship(AccountLead associateLead) {
		AccountLeadExample ex = new AccountLeadExample();
		ex.createCriteria().andAccountidEqualTo(associateLead.getAccountid())
				.andLeadidEqualTo(associateLead.getLeadid());
		accountLeadMapper.deleteByExample(ex);
	}
}
