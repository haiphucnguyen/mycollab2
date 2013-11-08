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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.interceptor.aspect.Watchable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.dao.LeadMapper;
import com.esofthead.mycollab.module.crm.dao.LeadMapperExt;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.schedule.email.crm.LeadRelayEmailNotificationAction;

@Service
@Transactional
@Traceable(module = "Crm", type = "Lead", nameField = "lastname")
@Auditable(module = "Crm", type = "Lead")
@Watchable(type = CrmTypeConstants.LEAD, userFieldName = "assignuser", emailHandlerBean = LeadRelayEmailNotificationAction.class)
public class LeadServiceImpl extends
		DefaultService<Integer, Lead, LeadSearchCriteria> implements
		LeadService {

	@Autowired
	private LeadMapper leadMapper;
	@Autowired
	private LeadMapperExt leadMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Lead> getCrudMapper() {
		return leadMapper;
	}

	@Override
	public ISearchableDAO<LeadSearchCriteria> getSearchMapper() {
		return leadMapperExt;
	}

	@Override
	public SimpleLead findById(int leadId, int sAccountId) {
		return leadMapperExt.findById(leadId);
	}
}
