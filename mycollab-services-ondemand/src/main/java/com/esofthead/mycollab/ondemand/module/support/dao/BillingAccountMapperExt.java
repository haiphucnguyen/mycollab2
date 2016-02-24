package com.esofthead.mycollab.ondemand.module.support.dao;

import com.esofthead.mycollab.ondemand.module.support.domain.SimpleBillingAccount;
import com.esofthead.mycollab.ondemand.module.support.domain.criteria.BillingAccountSearchCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public interface BillingAccountMapperExt {
    List<SimpleBillingAccount> findPagableListByCriteria(@Param("searchCriteria") BillingAccountSearchCriteria criteria, RowBounds rowBounds);
}
