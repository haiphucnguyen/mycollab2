/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.interceptor.aspect.Watchable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.dao.CallMapper;
import com.esofthead.mycollab.module.crm.dao.CallMapperExt;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.criteria.CallSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.service.EventService;
import com.esofthead.mycollab.schedule.email.crm.CallRelayEmailNotificationAction;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Service
@Transactional
@Traceable(module = ModuleNameConstants.CRM, type = CrmTypeConstants.CALL, nameField = "subject")
@Auditable(module = ModuleNameConstants.CRM, type = CrmTypeConstants.CALL)
@Watchable(type = CrmTypeConstants.CALL, userFieldName = "assignuser", emailHandlerBean = CallRelayEmailNotificationAction.class)
public class CallServiceImpl extends
		DefaultService<Integer, CallWithBLOBs, CallSearchCriteria> implements
		CallService {

	@Autowired
	protected CallMapper callMapper;
	@Autowired
	protected CallMapperExt callMapperExt;

	@SuppressWarnings("unchecked")
	@Override
	public ICrudGenericDAO<Integer, CallWithBLOBs> getCrudMapper() {
		return callMapper;
	}

	@Override
	public SimpleCall findById(int callId, int sAccountId) {
		return callMapperExt.findById(callId);
	}

	@Override
	public ISearchableDAO<CallSearchCriteria> getSearchMapper() {
		return callMapperExt;
	}

	@Override
	public int saveWithSession(CallWithBLOBs record, String username) {
		int result = super.saveWithSession(record, username);
		CacheUtils.cleanCaches(record.getSaccountid(), EventService.class);
		return result;
	}

	@Override
	public int updateWithSession(CallWithBLOBs record, String username) {
		int result = super.updateWithSession(record, username);
		CacheUtils.cleanCaches(record.getSaccountid(), EventService.class);
		return result;
	}

	@Override
	public int removeWithSession(Integer primaryKey, String username,
			int accountId) {
		int result = super.removeWithSession(primaryKey, username, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
		return result;
	}

	@Override
	public void removeByCriteria(CallSearchCriteria criteria, int accountId) {
		super.removeByCriteria(criteria, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
	}

	@Override
	public void massRemoveWithSession(List<Integer> primaryKeys,
			String username, int accountId) {
		super.massRemoveWithSession(primaryKeys, username, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
	}

	@Override
	public void massUpdateWithSession(CallWithBLOBs record,
			List<Integer> primaryKeys, int accountId) {
		super.massUpdateWithSession(record, primaryKeys, accountId);
		CacheUtils.cleanCaches(accountId, EventService.class);
	}

	@Override
	public void updateBySearchCriteria(CallWithBLOBs record,
			CallSearchCriteria searchCriteria) {
		super.updateBySearchCriteria(record, searchCriteria);
		CacheUtils.cleanCaches((Integer) searchCriteria.getSaccountid()
				.getValue(), EventService.class);
	}
}
