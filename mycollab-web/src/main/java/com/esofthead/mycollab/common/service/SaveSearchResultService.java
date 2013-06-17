package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.core.persistence.service.ICrudService;

public interface SaveSearchResultService extends
		ICrudService<Integer, SaveSearchResultWithBLOBs> {
	public String[] getListQueryName(String type);
	public String getQueryTextByName(String name,String type);
}
