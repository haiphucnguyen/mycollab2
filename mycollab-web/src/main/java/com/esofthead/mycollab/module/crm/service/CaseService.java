package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;

public interface CaseService extends
		IDefaultService<Integer, Case, CaseSearchCriteria> {

}
