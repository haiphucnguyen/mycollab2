package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.ProductCatalogMapper;
import com.esofthead.mycollab.module.crm.dao.ProductCatalogMapperExt;
import com.esofthead.mycollab.module.crm.domain.ProductCatalog;
import com.esofthead.mycollab.module.crm.domain.criteria.ProductCatalogSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ProductCatalogService;

@Service
@Transactional
@Traceable(module = "Crm", type = "ProductCatalog", nameField = "productname")
public class ProductCatalogServiceImpl extends
		DefaultService<Integer, ProductCatalog, ProductCatalogSearchCriteria>
		implements ProductCatalogService {

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
