package com.mycollab.pro.module.project.dao

import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria
import org.apache.ibatis.annotations.Param

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
interface ItemTimeLoggingMapperExt : ISearchableDAO<ItemTimeLoggingSearchCriteria> {

    fun getTotalHoursByCriteria(@Param("searchCriteria") criteria: ItemTimeLoggingSearchCriteria): Double
}
