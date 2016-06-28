package com.esofthead.mycollab.pro.common.dao;

import com.esofthead.mycollab.common.domain.AggregateTag;
import com.esofthead.mycollab.common.domain.criteria.TagSearchCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public interface TagMapperExt {
    List<AggregateTag> findPagableListByCriteria(@Param("searchCriteria") TagSearchCriteria criteria, RowBounds rowBounds);
}
