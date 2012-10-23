package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.core.persistence.IPagableService;
import com.esofthead.mycollab.module.crm.domain.ProductCatalog;
import com.esofthead.mycollab.module.crm.domain.criteria.ProductCatalogSearchCriteria;

public interface ProductCatalogService extends
        ICrudService<ProductCatalog, Integer>,
        IPagableService<ProductCatalogSearchCriteria> {

}
