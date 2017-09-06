package com.mycollab.module.crm.service.impl;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.crm.dao.ProductCatalogMapper;
import com.mycollab.module.crm.dao.ProductCatalogMapperExt;
import com.mycollab.module.crm.domain.ProductCatalog;
import com.mycollab.module.crm.domain.criteria.ProductCatalogSearchCriteria;
import com.mycollab.module.crm.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductCatalogServiceImpl extends DefaultService<Integer, ProductCatalog, ProductCatalogSearchCriteria> implements ProductCatalogService {

    @Autowired
    private ProductCatalogMapper productCatalogMapper;

    @Autowired
    private ProductCatalogMapperExt productCatalogMapperExt;

    @Override
    public ICrudGenericDAO<Integer, ProductCatalog> getCrudMapper() {
        return productCatalogMapper;
    }

    @Override
    public ISearchableDAO<ProductCatalogSearchCriteria> getSearchMapper() {
        return productCatalogMapperExt;
    }

}
