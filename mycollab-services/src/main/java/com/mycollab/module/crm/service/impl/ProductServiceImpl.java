package com.mycollab.module.crm.service.impl;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.crm.dao.ProductMapper;
import com.mycollab.module.crm.dao.ProductMapperExt;
import com.mycollab.module.crm.domain.Product;
import com.mycollab.module.crm.domain.criteria.ProductSearchCriteria;
import com.mycollab.module.crm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl extends DefaultService<Integer, Product, ProductSearchCriteria> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductMapperExt productMapperExt;

    @Override
    public ICrudGenericDAO<Integer, Product> getCrudMapper() {
        return productMapper;
    }

    @Override
    public ISearchableDAO<ProductSearchCriteria> getSearchMapper() {
        return productMapperExt;
    }
}
