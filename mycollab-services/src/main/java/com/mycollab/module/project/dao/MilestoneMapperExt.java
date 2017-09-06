package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import org.apache.ibatis.annotations.Param;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface MilestoneMapperExt extends ISearchableDAO<MilestoneSearchCriteria> {

    SimpleMilestone findById(Integer milestoneId);

    Double getTotalBillableHours(@Param("milestoneid") int milestoneId);

    Double getTotalNonBillableHours(@Param("milestoneid") int milestoneId);

    Double getRemainHours(@Param("milestoneid") int milestoneId);
}
