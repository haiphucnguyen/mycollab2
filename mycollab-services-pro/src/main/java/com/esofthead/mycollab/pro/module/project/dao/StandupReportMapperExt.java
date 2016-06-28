package com.esofthead.mycollab.pro.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.StandupReportStatistic;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface StandupReportMapperExt extends ISearchableDAO<StandupReportSearchCriteria> {

    SimpleStandupReport findReportById(Integer standupId);

    List<StandupReportStatistic> getProjectReportsStatistic(@Param("projectIds") List<Integer> projectIds, @Param
            ("onDate") Date onDate, RowBounds rowBounds);

    List<SimpleUser> findUsersNotDoReportYet(@Param("projectId") Integer projectId, @Param("onDate") Date onDate);
}
