package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;

public interface CaseService extends
        IDefaultService<Integer, CaseWithBLOBs, CaseSearchCriteria> {

    SimpleCase findCaseById(int caseId);
}
