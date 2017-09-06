package com.mycollab.module.crm.service;

import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.Product;
import com.mycollab.module.crm.domain.criteria.ProductSearchCriteria;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ProductService extends IDefaultService<Integer, Product, ProductSearchCriteria> {

}
