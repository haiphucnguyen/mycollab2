package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import org.apache.ibatis.annotations.Param;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface EventMapperExt extends ISearchableDAO<ActivitySearchCriteria> {
    int getTotalCountFromTask(@Param("searchCriteria") ActivitySearchCriteria criteria);

    int getTotalCountFromCall(@Param("searchCriteria") ActivitySearchCriteria criteria);

    int getTotalCountFromMeeting(@Param("searchCriteria") ActivitySearchCriteria criteria);
}
