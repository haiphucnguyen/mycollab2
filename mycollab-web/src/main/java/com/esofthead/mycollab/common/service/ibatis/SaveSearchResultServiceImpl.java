package com.esofthead.mycollab.common.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.SaveSearchResultMapper;
import com.esofthead.mycollab.common.dao.SaveSearchResultMapperExt;
import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.common.service.SaveSearchResultService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;

@Service
public class SaveSearchResultServiceImpl extends
		DefaultCrudService<Integer, SaveSearchResultWithBLOBs> implements
		SaveSearchResultService {

	@Autowired
	private SaveSearchResultMapper saveSearchResultMapper;
	
	@Autowired
	private SaveSearchResultMapperExt saveSearchResultMapperExt;

	@Override
	public ICrudGenericDAO<Integer, SaveSearchResultWithBLOBs> getCrudMapper() {
		return saveSearchResultMapper;
	}

	@Override
	public String[] getListQueryName(String type) {
		return saveSearchResultMapperExt.getListQueryName(type);
	}

	@Override
	public String getQueryTextByName(String name, String type) {
		return saveSearchResultMapperExt.getQueryTextByName(name, type);
	}

}
