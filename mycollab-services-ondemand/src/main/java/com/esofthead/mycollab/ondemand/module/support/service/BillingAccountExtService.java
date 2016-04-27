package com.esofthead.mycollab.ondemand.module.support.service;

import com.esofthead.mycollab.core.arguments.BasicSearchRequest;
import com.esofthead.mycollab.ondemand.module.support.domain.SimpleBillingAccount2;
import com.esofthead.mycollab.ondemand.module.support.domain.criteria.BillingAccountSearchCriteria;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public interface BillingAccountExtService {
    List<SimpleBillingAccount2> findPagableListByCriteria(BasicSearchRequest<BillingAccountSearchCriteria> searchRequest);
}
