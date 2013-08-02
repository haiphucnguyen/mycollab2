package com.esofthead.mycollab.module.ecm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.ecm.dao.ContentActivityLogMapper;
import com.esofthead.mycollab.module.ecm.dao.ContentActivityLogMapperExt;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLog;
import com.esofthead.mycollab.module.ecm.domain.criteria.ContentActivityLogSearchCriteria;
import com.esofthead.mycollab.module.ecm.service.ContentActivityLogService;

@Service
public class ContentActivityLogServiceImpl
		extends
		DefaultService<Integer, ContentActivityLog, ContentActivityLogSearchCriteria>
		implements ContentActivityLogService {

	@Autowired
	private ContentActivityLogMapper contentActivityLogMapper;

	private ContentActivityLogMapperExt contentActivityLogMapperExt;

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return contentActivityLogMapper;
	}

	@Override
	public ISearchableDAO<ContentActivityLogSearchCriteria> getSearchMapper() {
		return contentActivityLogMapperExt;
	}

}
