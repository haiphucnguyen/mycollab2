package com.esofthead.mycollab.common.dao;

import java.util.List;

import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface SaveSearchResultMapperExt<S extends SearchCriteria> extends SaveSearchResultMapper{
	public abstract List<SaveSearchResultWithBLOBs> getListSaveSearchResult(S searchCriteria);
}
