package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;

public interface StandupReportMapperExt extends
		ISearchableDAO<StandupReportSearchCriteria> {

	SimpleStandupReport findReportById(int standupId);

	List<GroupItem> getReportsCount(
			@Param("searchCriteria") StandupReportSearchCriteria criteria);

}
