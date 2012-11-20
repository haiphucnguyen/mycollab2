package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.crm.dao.TargetGroupMapper;
import com.esofthead.mycollab.module.crm.dao.TargetGroupMapperExt;
import com.esofthead.mycollab.module.crm.domain.TargetGroup;
import com.esofthead.mycollab.module.crm.domain.criteria.TargetGroupSearchCriteria;
import com.esofthead.mycollab.module.crm.service.TargetGroupService;

@Service
public class TargetGroupServiceImpl extends
		DefaultService<Integer, TargetGroup, TargetGroupSearchCriteria> implements TargetGroupService {

	@Autowired
	private TargetGroupMapper targetGroupMapper;
	
	@Autowired
	private TargetGroupMapperExt targetGroupMapperExt;

	@Override
	public ICrudGenericDAO<Integer, TargetGroup> getCrudMapper() {
		return targetGroupMapper;
	}

	@Override
	public ISearchableDAO<TargetGroupSearchCriteria> getSearchMapper() {
		return targetGroupMapperExt;
	}

}
