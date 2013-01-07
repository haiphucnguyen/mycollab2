/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.MessageDispatcher;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.PermissionPaths;
import com.esofthead.mycollab.module.project.dao.PermissionMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMapperExt;
import com.esofthead.mycollab.module.project.domain.Permission;
import com.esofthead.mycollab.module.project.domain.PermissionExample;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectExample;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = "Project", nameField = "name", type = "Project", extraFieldName="id")
public class ProjectServiceImpl extends DefaultService<Integer, Project, ProjectSearchCriteria> implements
        ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectMapperExt projectMapperExt;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private MessageDispatcher messageDispatcher;

    @Override
    public ICrudGenericDAO<Integer, Project> getCrudMapper() {
        return projectMapper;
    }

    @Override
    public ISearchableDAO<ProjectSearchCriteria> getSearchMapper() {
        return projectMapperExt;
    }

    @Override
    protected int internalUpdateWithSession(Project record, String username) {
        super.internalUpdateWithSession(record, username);
        PermissionExample ex = new PermissionExample();
        if (record.getOwner() != null && record.getOwner() != "") {
            ex.createCriteria().andProjectidEqualTo(record.getId())
                    .andUsernameEqualTo(record.getOwner());
            permissionMapper.deleteByExample(ex);
            setAllPermissions(record.getId(), record.getOwner());
        }

        return 0;
    }

    @Override
    public int saveWithSession(Project record, String username) {
        if (isExistProjectHasSameName(record.getName())) {
            throw new MyCollabException("There is project has name "
                    + record.getName()
                    + " already. Please choose another project name");
        }

        int projectid = super.saveWithSession(record, username);

        if (record.getOwner() != null && record.getOwner() != "") {
            // set all project permissions to project owner
            setAllPermissions(projectid, record.getOwner());
        }

        return projectid;
    }

    private void setAllPermissions(int projectid, String user) {
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.EDIT_MEMBERS));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.EDIT_MESSAGES));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.EDIT_PERMISSIONS));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.EDIT_PROBLEMS));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.EDIT_RISKS));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.EDIT_TASKS));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.VIEW_MEMBERS));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.VIEW_MESSAGES));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.VIEW_PERMISSIONS));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.VIEW_PROBLEMS));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.VIEW_RISKS));
        permissionMapper.insert(createPermission(projectid, user,
                PermissionPaths.VIEW_TASKS));
    }

    private Permission createPermission(int projectid, String user,
            String permissionPath) {
        Permission permission = new Permission();
        permission.setCanaccess(true);
        permission.setPathid(permissionPath);
        permission.setProjectid(projectid);
        permission.setUsername(user);
        return permission;
    }

    @Override
    public boolean isExistProjectHasSameName(String name) {
        ProjectExample ex = new ProjectExample();
        ex.createCriteria().andNameEqualTo(name);
        List<Project> list = projectMapper.selectByExample(ex);
        return (list != null && list.size() > 0);
    }

    @Override
    public List<SimpleProject> getInvolvedProjectOfUser(String username) {
        return projectMapperExt.getInvolvedProjectOfUser(username);
    }
}
