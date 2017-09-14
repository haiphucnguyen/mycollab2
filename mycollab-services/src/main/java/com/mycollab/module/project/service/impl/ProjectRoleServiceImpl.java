package com.mycollab.module.project.service.impl;

import com.mycollab.aspect.ClassInfo;
import com.mycollab.aspect.ClassInfoMap;
import com.mycollab.common.ModuleNameConstants;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.core.utils.JsonDeSerializer;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.dao.ProjectRoleMapper;
import com.mycollab.module.project.dao.ProjectRoleMapperExt;
import com.mycollab.module.project.dao.ProjectRolePermissionMapper;
import com.mycollab.module.project.domain.ProjectRole;
import com.mycollab.module.project.domain.ProjectRolePermission;
import com.mycollab.module.project.domain.ProjectRolePermissionExample;
import com.mycollab.module.project.domain.SimpleProjectRole;
import com.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.mycollab.module.project.service.ProjectRoleService;
import com.mycollab.security.PermissionMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class ProjectRoleServiceImpl extends DefaultService<Integer, ProjectRole, ProjectRoleSearchCriteria> implements ProjectRoleService {

    static {
        ClassInfoMap.put(ProjectRoleServiceImpl.class, new ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.PROJECT_ROLE));
    }

    @Autowired
    private ProjectRoleMapper roleMapper;

    @Autowired
    private ProjectRoleMapperExt roleMapperExt;

    @Autowired
    private ProjectRolePermissionMapper projectRolePermissionMapper;

    @Override
    public ICrudGenericDAO<Integer, ProjectRole> getCrudMapper() {
        return roleMapper;
    }

    @Override
    public ISearchableDAO<ProjectRoleSearchCriteria> getSearchMapper() {
        return roleMapperExt;
    }

    @Override
    public void savePermission(Integer projectId, Integer roleId, PermissionMap permissionMap, Integer sAccountId) {
        String perVal = JsonDeSerializer.toJson(permissionMap);

        ProjectRolePermissionExample ex = new ProjectRolePermissionExample();
        ex.createCriteria().andRoleidEqualTo(roleId);

        ProjectRolePermission rolePer = new ProjectRolePermission();
        rolePer.setRoleid(roleId);
        rolePer.setProjectid(projectId);
        rolePer.setRoleval(perVal);

        Long data = projectRolePermissionMapper.countByExample(ex);
        if (data > 0) {
            projectRolePermissionMapper.updateByExampleSelective(rolePer, ex);
        } else {
            projectRolePermissionMapper.insert(rolePer);
        }

    }

    @Override
    public SimpleProjectRole findById(Integer roleId, Integer sAccountId) {
        return roleMapperExt.findRoleById(roleId);
    }
}
