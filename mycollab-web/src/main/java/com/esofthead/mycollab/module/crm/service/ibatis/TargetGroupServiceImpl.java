package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.TargetGroupMapperExt;
import com.esofthead.mycollab.module.crm.domain.TargetGroup;
import com.esofthead.mycollab.module.crm.domain.criteria.TargetGroupSearchCriteria;
import com.esofthead.mycollab.module.crm.service.TargetGroupService;

public class TargetGroupServiceImpl extends
		DefaultCrudService<TargetGroup, Integer> implements TargetGroupService {

	private TargetGroupMapperExt targetGroupExtDAO;

	public void setTargetGroupExtDAO(TargetGroupMapperExt targetGroupExtDAO) {
		this.targetGroupExtDAO = targetGroupExtDAO;
	}

	@Override
	public List findPagableListByCriteria(TargetGroupSearchCriteria criteria,
			int skipNum, int maxResult) {
		return targetGroupExtDAO.findPagableList(criteria, new RowBounds(
				skipNum, maxResult));
	}

	@Override
	public int getTotalCount(TargetGroupSearchCriteria criteria) {
		return targetGroupExtDAO.getTotalCount(criteria);
	}

}
