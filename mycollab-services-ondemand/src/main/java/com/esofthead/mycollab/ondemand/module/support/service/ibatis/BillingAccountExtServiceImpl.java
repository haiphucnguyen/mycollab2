package com.esofthead.mycollab.ondemand.module.support.service.ibatis;

import com.esofthead.mycollab.core.arguments.BasicSearchRequest;
import com.esofthead.mycollab.ondemand.module.support.dao.BillingAccountMapperExt2;
import com.esofthead.mycollab.ondemand.module.support.domain.SimpleBillingAccount2;
import com.esofthead.mycollab.ondemand.module.support.domain.criteria.BillingAccountSearchCriteria;
import com.esofthead.mycollab.ondemand.module.support.service.BillingAccountExtService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
@Service
public class BillingAccountExtServiceImpl implements BillingAccountExtService {
    @Autowired
    private BillingAccountMapperExt2 billingAccountMapperExt2;

    @Override
    public List<SimpleBillingAccount2> findPagableListByCriteria(BasicSearchRequest<BillingAccountSearchCriteria> searchRequest) {
        return billingAccountMapperExt2.findPagableListByCriteria(searchRequest.getSearchCriteria(),
                new RowBounds((searchRequest.getCurrentPage() - 1) * searchRequest.getNumberOfItems(),
                        searchRequest.getNumberOfItems()));
    }
}
