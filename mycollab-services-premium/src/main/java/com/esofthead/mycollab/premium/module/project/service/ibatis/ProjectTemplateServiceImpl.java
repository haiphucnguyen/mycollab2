package com.esofthead.mycollab.premium.module.project.service.ibatis;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.service.*;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author myCollab Ltd
 * @since 5.2.8
 */
@Service
public class ProjectTemplateServiceImpl implements ProjectTemplateService {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectTemplateService.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRoleService projectRoleService;

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private BugService bugService;

    @Autowired
    private RiskService riskService;

    @Autowired
    private MessageService messageService;

    @Override
    public Integer cloneProject(Integer projectId, Integer sAccountId, String username) {
        SimpleProject project = projectService.findById(projectId, sAccountId);
        if (project != null) {
            LOG.info("Clone project info");
            project.setId(null);
            Integer newProjectId = projectService.saveWithSession(project, username);
            cloneProjectRoles(projectId, newProjectId, sAccountId, username);
            LOG.info("Clone project members");

            LOG.info("Clone project tasks");

            LOG.info("Clone project issues");

            LOG.info("Clone project risks");

            LOG.info("Clone project messages");
        }
        return null;
    }

    private void cloneProjectRoles(Integer projectId, Integer newProjectId, Integer sAccountId, String username) {
        LOG.info("Clone project roles");
        ProjectRoleSearchCriteria searchCriteria = new ProjectRoleSearchCriteria();
        searchCriteria.setProjectId(new NumberSearchField(projectId));
        List<SimpleProjectRole> roles = projectRoleService.findAbsoluteListByCriteria(searchCriteria, 0, Integer.MAX_VALUE);
        for (SimpleProjectRole role : roles) {
            role.setId(null);
            role.setProjectid(newProjectId);
            int roleId = projectRoleService.saveWithSession(role, username);
            projectRoleService.savePermission(projectId, roleId, role.getPermissionMap(), sAccountId);
        }
    }

    private void cloneProjectMembers(Integer projectId, Integer newProjectId, Integer sAccountId) {
        List<SimpleUser> activeUsersInProject = projectMemberService.getActiveUsersInProject(projectId, sAccountId);
    }
}
