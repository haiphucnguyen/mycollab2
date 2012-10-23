package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.criteria.ContractSearchCriteria;

public interface ContractMapperExt {
	public List findPagableList(ContractSearchCriteria criteria,
			RowBounds rowBounds);

	public int getTotalCount(ContractSearchCriteria criteria);
}
