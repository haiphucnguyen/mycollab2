package com.mycollab.pro.common.dao

import com.mycollab.common.domain.AggregateTag
import com.mycollab.common.domain.criteria.TagSearchCriteria
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.session.RowBounds

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
interface TagMapperExt {
    fun findPageableListByCriteria(@Param("searchCriteria") criteria: TagSearchCriteria, rowBounds: RowBounds): List<AggregateTag>
}
