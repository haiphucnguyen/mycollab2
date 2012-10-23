package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.criteria.ProductSearchCriteria;

public interface ProductMapperExt {
	public List findPagableList(ProductSearchCriteria criteria,
			RowBounds rowBounds);

	public int getTotalCount(ProductSearchCriteria criteria);
}
