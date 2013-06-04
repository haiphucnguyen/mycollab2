package com.esofthead.mycollab.module.crm.service.ibatis;

import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.ProductMapper;
import com.esofthead.mycollab.module.crm.dao.ProductMapperExt;
import com.esofthead.mycollab.module.crm.domain.Product;
import com.esofthead.mycollab.module.crm.domain.criteria.ProductSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = "Crm", type = "Product", nameField = "productname")
@Auditable(module = "Crm", type = "Product")
public class ProductServiceImpl extends DefaultService<Integer, Product, ProductSearchCriteria>
        implements ProductService {

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
