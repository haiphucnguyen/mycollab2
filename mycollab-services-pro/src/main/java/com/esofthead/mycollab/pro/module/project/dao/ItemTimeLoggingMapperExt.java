package com.esofthead.mycollab.pro.module.project.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import org.apache.ibatis.annotations.Param;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface ItemTimeLoggingMapperExt extends ISearchableDAO<ItemTimeLoggingSearchCriteria> {

    Double getTotalHoursByCriteria(@Param("searchCriteria") ItemTimeLoggingSearchCriteria criteria);
}
