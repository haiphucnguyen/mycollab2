package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.core.persistence.IPagableService;
import com.esofthead.mycollab.module.crm.domain.TargetGroup;
import com.esofthead.mycollab.module.crm.domain.criteria.TargetGroupSearchCriteria;

public interface TargetGroupService extends ICrudService<TargetGroup, Integer>,
        IPagableService<TargetGroupSearchCriteria> {

}
