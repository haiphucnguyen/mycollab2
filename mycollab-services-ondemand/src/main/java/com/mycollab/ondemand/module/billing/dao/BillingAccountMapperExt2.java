package com.mycollab.ondemand.module.billing.dao;

import com.mycollab.module.user.domain.BillingAccountWithOwners;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingAccount2;
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public interface BillingAccountMapperExt2 {
    List<SimpleBillingAccount2> findPagableListByCriteria(@Param("searchCriteria") BillingAccountSearchCriteria criteria,
                                                          RowBounds rowBounds);

    List<String> getSubDomainsOfUser(@Param("username") String username);

    List<BillingAccountWithOwners> getTrialAccountsWithOwners();
}
