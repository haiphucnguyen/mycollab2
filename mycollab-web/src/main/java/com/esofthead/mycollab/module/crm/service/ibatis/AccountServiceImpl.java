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
package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.crm.Constants;
import com.esofthead.mycollab.module.crm.dao.AccountMapper;
import com.esofthead.mycollab.module.crm.dao.AccountMapperExt;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.AccountExample;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.TaskExample;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

@Service
public class AccountServiceImpl extends
		DefaultService<Integer, Account, AccountSearchCriteria> implements
		AccountService {

	@Autowired
	protected AccountMapper accountMapper;

	@Autowired
	protected AccountMapperExt accountMapperExt;

	@Autowired
	protected TaskMapper taskMapper;

	@Autowired
	protected AuditLogService auditLogService;

	@Override
	public ICrudGenericDAO<Integer, Account> getCrudMapper() {
		return accountMapper;
	}

	@Override
	public ISearchableDAO<AccountSearchCriteria> getSearchMapper() {
		return accountMapperExt;
	}

	@Override
	public int updateWithSession(Account record, String username) {
		Account oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-account-" + record.getId();
		auditLogService.saveAuditLog(username, refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, username);
	}

	@Override
	public int remove(Integer primaryKey) {
		int result = super.remove(primaryKey);
		TaskExample ex = new TaskExample();
		ex.createCriteria().andTypeEqualTo(Constants.ACCOUNT)
				.andTypeidEqualTo(primaryKey);
		taskMapper.deleteByExample(ex);
		return result;
	}

	public SimpleAccount findAccountById(int accountId) {
		return accountMapperExt.findAccountById(accountId);
	}

	@Override
	public void removeWithSession(List<Integer> primaryKeys, String username) {
		AccountExample ex = new AccountExample();
		ex.createCriteria().andIdIn(primaryKeys);
		accountMapper.deleteByExample(ex);
	}

}
