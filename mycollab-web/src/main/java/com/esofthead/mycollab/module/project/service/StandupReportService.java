package com.esofthead.mycollab.module.project.service;

import java.util.Date;

import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.StandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;

public interface StandupReportService extends
		IDefaultService<Integer, StandupReport, StandupReportSearchCriteria> {
	SimpleStandupReport findStandupReportById(int standupId);

	SimpleStandupReport findStandupReportByDateUser(int projectId,
			String username, Date onDate);;

}
