/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapperExt;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author haiphucnguyen
 */
@Service
public class ProjectMemberServiceImpl extends DefaultService<Integer, ProjectMember, ProjectMemberSearchCriteria>
        implements ProjectMemberService {

    @Autowired
    protected ProjectMemberMapper projectMemberMapper;
    
    @Autowired
    protected ProjectMemberMapperExt projectMemberMapperExt;
    
    @Override
    public ICrudGenericDAO getCrudMapper() {
        return projectMemberMapper;
    }

    @Override
    public ISearchableDAO<ProjectMemberSearchCriteria> getSearchMapper() {
        return projectMemberMapperExt;
    }
    
}
