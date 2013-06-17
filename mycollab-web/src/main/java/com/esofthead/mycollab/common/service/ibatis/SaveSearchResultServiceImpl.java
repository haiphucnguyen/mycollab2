package com.esofthead.mycollab.common.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.SaveSearchResultMapper;
import com.esofthead.mycollab.common.dao.SaveSearchResultMapperExt;
import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.common.domain.criteria.SaveSearchResultCriteria;
import com.esofthead.mycollab.common.service.SaveSearchResultService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;

@Service
public class SaveSearchResultServiceImpl
		extends
		DefaultService<Integer, SaveSearchResultWithBLOBs, SaveSearchResultCriteria>
		implements SaveSearchResultService {

	@Autowired
	private SaveSearchResultMapper saveSearchResultMapper;

	@Autowired
	private SaveSearchResultMapperExt saveSearchResultMapperExt;

	@Override
	public ICrudGenericDAO<Integer, SaveSearchResultWithBLOBs> getCrudMapper() {
		return saveSearchResultMapper;
	}

	@Override
	public ISearchableDAO<SaveSearchResultCriteria> getSearchMapper() {
		return saveSearchResultMapperExt;
	}
}
