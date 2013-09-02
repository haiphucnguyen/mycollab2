package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultSearchService;
import com.esofthead.mycollab.module.project.dao.ProjectFollowingTicketMapperExt;
import com.esofthead.mycollab.module.project.service.ProjectFollowingTicketService;

@Service
public class ProjectFollowingTicketServiceImpl extends
		DefaultSearchService<MonitorSearchCriteria> implements
		ProjectFollowingTicketService {

	@Autowired
	private ProjectFollowingTicketMapperExt projectFollowingTicketMapperExt;

	@Override
	public ISearchableDAO<MonitorSearchCriteria> getSearchMapper() {
		return projectFollowingTicketMapperExt;
	}

}
