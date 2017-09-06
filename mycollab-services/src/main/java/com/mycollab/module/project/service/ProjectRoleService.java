package com.mycollab.module.project.service;

import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.project.domain.ProjectRole;
import com.mycollab.module.project.domain.SimpleProjectRole;
import com.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.mycollab.security.PermissionMap;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface ProjectRoleService extends IDefaultService<Integer, ProjectRole, ProjectRoleSearchCriteria> {

    @CacheEvict
    void savePermission(Integer projectId, Integer roleId, PermissionMap permissionMap, @CacheKey Integer sAccountId);

    @Cacheable
    SimpleProjectRole findById(Integer roleId, @CacheKey Integer sAccountId);
}
