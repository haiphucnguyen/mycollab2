package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.core.persistence.IPagableService;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;

public interface CaseService extends ICrudService<Case, Integer>,
        IPagableService<CaseSearchCriteria> {

}
