package com.mycollab.ondemand.module.billing.service;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingAccount2;
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public interface BillingAccountExtService {
    List<SimpleBillingAccount2> findPagableListByCriteria(BasicSearchRequest<BillingAccountSearchCriteria> searchRequest);
}
