/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.dao.MilestoneMapper;
import com.esofthead.mycollab.module.project.dao.MilestoneMapperExt;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author haiphucnguyen
 */
@Service
@Transactional
@Traceable(module = "Project", type = "Milestone", nameField = "name")
public class MilestoneServiceImpl extends DefaultService<Integer, Milestone, MilestoneSearchCriteria> implements
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
    public SimpleMilestone findMilestoneById(int milestoneId) {
        return milestoneMapperExt.findMilestoneById(milestoneId);
    }
}
