package com.esofthead.mycollab.ondemand.module.support.service;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.ondemand.module.support.domain.SimpleBillingAccount;
import com.esofthead.mycollab.ondemand.module.support.domain.criteria.BillingAccountSearchCriteria;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public interface BillingAccountExtService {
    List<SimpleBillingAccount> findPagableListByCriteria(SearchRequest<BillingAccountSearchCriteria> searchRequest);
}
