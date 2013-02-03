package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.dao.ProjectRoleMapper;
import com.esofthead.mycollab.module.project.dao.ProjectRoleMapperExt;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectRoleServiceImpl extends DefaultService<Integer, ProjectRole, ProjectRoleSearchCriteria>
        implements ProjectRoleService {

    @Autowired
    private ProjectRoleMapper roleMapper;
    @Autowired
    private ProjectRoleMapperExt roleMapperExt;

    @Override
    public ICrudGenericDAO<Integer, ProjectRole> getCrudMapper() {
        return roleMapper;
    }

    @Override
    public ISearchableDAO<ProjectRoleSearchCriteria> getSearchMapper() {
        return roleMapperExt;
    }
}
