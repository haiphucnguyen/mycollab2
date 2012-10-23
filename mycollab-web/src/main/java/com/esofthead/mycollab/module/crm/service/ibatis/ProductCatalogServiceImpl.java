package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.ProductCatalogMapperExt;
import com.esofthead.mycollab.module.crm.domain.ProductCatalog;
import com.esofthead.mycollab.module.crm.domain.criteria.ProductCatalogSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ProductCatalogService;

public class ProductCatalogServiceImpl extends
		DefaultCrudService<ProductCatalog, Integer> implements
		ProductCatalogService {

	private ProductCatalogMapperExt productCatalogExtDAO;

	public void setProductCatalogExtDAO(
			ProductCatalogMapperExt productCatalogExtDAO) {
		this.productCatalogExtDAO = productCatalogExtDAO;
	}

	@Override
	public List findPagableListByCriteria(
			ProductCatalogSearchCriteria criteria, int skipNum, int maxResult) {
		return productCatalogExtDAO.findPagableList(criteria, new RowBounds(
				skipNum, maxResult));
	}

	@Override
	public int getTotalCount(ProductCatalogSearchCriteria criteria) {
		return productCatalogExtDAO.getTotalCount(criteria);
	}

}
