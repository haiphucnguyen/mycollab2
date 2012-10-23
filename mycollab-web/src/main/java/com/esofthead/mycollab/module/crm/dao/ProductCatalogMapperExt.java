package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.criteria.ProductCatalogSearchCriteria;

public interface ProductCatalogMapperExt {
	public List findPagableList(ProductCatalogSearchCriteria criteria,
			RowBounds rowBounds);

	public int getTotalCount(ProductCatalogSearchCriteria criteria);
}
