package com.mycollab.ondemand.module.support.dao;

import com.mycollab.ondemand.module.support.domain.SimpleBillingAccount2;
import com.mycollab.ondemand.module.support.domain.criteria.BillingAccountSearchCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public interface BillingAccountMapperExt2 {
    List<SimpleBillingAccount2> findPagableListByCriteria(@Param("searchCriteria") BillingAccountSearchCriteria criteria, RowBounds rowBounds);
}
