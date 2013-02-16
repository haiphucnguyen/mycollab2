package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.dao.StandupReportMapper;
import com.esofthead.mycollab.module.project.dao.StandupReportMapperExt;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.StandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.service.StandupReportService;

@Service
public class StandupReportServiceImpl extends
		DefaultService<Integer, StandupReport, StandupReportSearchCriteria>
		implements StandupReportService {
	@Autowired
	private StandupReportMapper standupReportMapper;
	@Autowired
	private StandupReportMapperExt standupReportMapperExt;

	@Override
	public SimpleStandupReport findStandupReportById(int standupId) {
		return standupReportMapperExt.findReportById(standupId);
	}

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return standupReportMapper;
	}

	@Override
	public ISearchableDAO<StandupReportSearchCriteria> getSearchMapper() {
		return standupReportMapperExt;
	}

}
