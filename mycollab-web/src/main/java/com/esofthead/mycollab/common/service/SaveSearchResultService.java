package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.common.domain.criteria.SaveSearchResultCriteria;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;

public interface SaveSearchResultService
		extends
		IDefaultService<Integer, SaveSearchResultWithBLOBs, SaveSearchResultCriteria> {
}
