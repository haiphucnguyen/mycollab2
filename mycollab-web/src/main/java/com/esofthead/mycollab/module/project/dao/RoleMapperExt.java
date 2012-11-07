package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.criteria.RoleSearchCriteria;

public interface RoleMapperExt {
	int getTotalCount(RoleSearchCriteria criteria);

    List<Risk> findPagableList(RoleSearchCriteria criteria, RowBounds rowBounds);
}
