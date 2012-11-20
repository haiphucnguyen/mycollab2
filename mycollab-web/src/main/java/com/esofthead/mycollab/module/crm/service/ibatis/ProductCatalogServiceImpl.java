package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.crm.dao.ProductCatalogMapper;
import com.esofthead.mycollab.module.crm.dao.ProductCatalogMapperExt;
import com.esofthead.mycollab.module.crm.domain.ProductCatalog;
import com.esofthead.mycollab.module.crm.domain.criteria.ProductCatalogSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ProductCatalogService;

public class ProductCatalogServiceImpl extends
		DefaultService<Integer, ProductCatalog, ProductCatalogSearchCriteria> implements
		ProductCatalogService {

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
