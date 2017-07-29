package com.mycollab.module.crm.service.impl;

import com.mycollab.module.crm.dao.TargetGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.crm.dao.TargetGroupMapperExt;
import com.mycollab.module.crm.domain.TargetGroup;
import com.mycollab.module.crm.domain.criteria.TargetGroupSearchCriteria;
import com.mycollab.module.crm.service.TargetGroupService;

@Service
@Transactional
public class TargetGroupServiceImpl extends DefaultService<Integer, TargetGroup, TargetGroupSearchCriteria> implements TargetGroupService {

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
