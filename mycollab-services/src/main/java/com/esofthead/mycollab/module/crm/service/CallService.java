package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.criteria.CallSearchCriteria;

public interface CallService extends
		IDefaultService<Integer, CallWithBLOBs, CallSearchCriteria> {

	@Cacheable
	SimpleCall findById(int callId, @CacheKey int sAccountId);
}
