package com.esofthead.mycollab.common.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ICrudService;

public interface SaveSearchResultService<S extends SearchCriteria> extends
		ICrudService<Integer, SaveSearchResultWithBLOBs> {
	public abstract List<SaveSearchResultWithBLOBs> getListSaveSearchResult(S searchCriteria);
}
