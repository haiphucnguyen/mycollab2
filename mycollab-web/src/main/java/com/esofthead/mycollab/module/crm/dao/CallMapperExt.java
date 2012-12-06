package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.module.crm.domain.SimpleCall;

public interface CallMapperExt {
	SimpleCall findCallById(int callId);
}
