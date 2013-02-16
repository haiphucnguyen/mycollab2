package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;

public interface StandupReportMapperExt extends ISearchableDAO<StandupReportSearchCriteria>{
	
	SimpleStandupReport findReportById(int standupId);

}
