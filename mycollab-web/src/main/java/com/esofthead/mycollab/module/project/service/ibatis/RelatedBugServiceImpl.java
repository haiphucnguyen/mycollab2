package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.tracker.dao.RelatedBugMapper;
import com.esofthead.mycollab.module.tracker.dao.RelatedBugMapperExt;
import com.esofthead.mycollab.module.tracker.domain.SimpleRelatedBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugRelatedSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.RelatedBugService;

@Service
public class RelatedBugServiceImpl extends
		DefaultService<Integer, SimpleRelatedBug, BugRelatedSearchCriteria>
		implements RelatedBugService {

	@Autowired
	private RelatedBugMapper relatedBugMapper;

	@Autowired
	private RelatedBugMapperExt relatedTimeLoggingMapperExt;

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return relatedBugMapper;
	}

	@Override
	public ISearchableDAO<BugRelatedSearchCriteria> getSearchMapper() {
		return relatedTimeLoggingMapperExt;
	}

}
