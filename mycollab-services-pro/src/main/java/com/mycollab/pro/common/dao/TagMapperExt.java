package com.mycollab.pro.common.dao;

import com.mycollab.common.domain.AggregateTag;
import com.mycollab.common.domain.criteria.TagSearchCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public interface TagMapperExt {
    List<AggregateTag> findPageableListByCriteria(@Param("searchCriteria") TagSearchCriteria criteria, RowBounds rowBounds);
}
