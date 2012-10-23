package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.core.persistence.IPagableService;
import com.esofthead.mycollab.module.crm.domain.Product;
import com.esofthead.mycollab.module.crm.domain.criteria.ProductSearchCriteria;

public interface ProductService extends ICrudService<Product, Integer>,
        IPagableService<ProductSearchCriteria> {

}
