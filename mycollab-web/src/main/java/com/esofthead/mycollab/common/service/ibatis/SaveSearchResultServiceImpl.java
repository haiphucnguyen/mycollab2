package com.esofthead.mycollab.common.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.SaveSearchResultMapper;
import com.esofthead.mycollab.common.dao.SaveSearchResultMapperExt;
import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.common.domain.criteria.SaveSearchResultCriteria;
import com.esofthead.mycollab.common.service.SaveSearchResultService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;

@Service
public class SaveSearchResultServiceImpl extends
		DefaultCrudService<Integer, SaveSearchResultWithBLOBs> implements
		SaveSearchResultService<SaveSearchResultCriteria> {

	@Autowired
	private SaveSearchResultMapper saveSearchResultMapper;
	
	@Autowired
	private SaveSearchResultMapperExt saveSearchResultMapperExt;

	@Override
	public ICrudGenericDAO<Integer, SaveSearchResultWithBLOBs> getCrudMapper() {
		return saveSearchResultMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaveSearchResultWithBLOBs> getListSaveSearchResult(
			SaveSearchResultCriteria searchCriteria) {
		return saveSearchResultMapperExt.getListSaveSearchResult(searchCriteria);
	}
}
