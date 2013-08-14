/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.MilestoneMapper;
import com.esofthead.mycollab.module.project.dao.MilestoneMapperExt;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.service.MilestoneService;

/**
 * 
 * @author haiphucnguyen
 */
@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, type = ProjectContants.MILESTONE, nameField = "name", extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.MILESTONE)
public class MilestoneServiceImpl extends
		DefaultService<Integer, Milestone, MilestoneSearchCriteria> implements
		MilestoneService {

	@Autowired
	protected MilestoneMapper milestoneMapper;
	@Autowired
	protected MilestoneMapperExt milestoneMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Milestone> getCrudMapper() {
		return milestoneMapper;
	}

	@Override
	public ISearchableDAO<MilestoneSearchCriteria> getSearchMapper() {
		return milestoneMapperExt;
	}

	@Override
	public SimpleMilestone findById(int milestoneId, int sAccountId) {
		return milestoneMapperExt.findById(milestoneId);
	}
}
