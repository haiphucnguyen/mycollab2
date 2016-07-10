package com.mycollab.ondemand.module.billing.service.ibatis;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.ondemand.module.billing.dao.BillingAccountMapperExt2;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingAccount2;
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria;
import com.mycollab.ondemand.module.billing.service.BillingAccountExtService;
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
