package com.mycollab.pro.module.project.service.ibatis;

import com.mycollab.common.dao.OptionValMapper;
import com.mycollab.common.domain.OptionVal;
import com.mycollab.common.domain.OptionValExample;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.ProjectMemberStatusConstants;
import com.mycollab.module.project.domain.*;
import com.mycollab.module.project.domain.criteria.*;
import com.mycollab.module.project.service.*;
import com.mycollab.module.tracker.dao.BugRelatedItemMapper;
import com.mycollab.module.tracker.domain.*;
import com.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.module.tracker.service.ComponentService;
import com.mycollab.module.tracker.service.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private BugRelatedItemMapper bugRelatedItemMapper;

    @Autowired
    private MilestoneService milestoneService;

    @Autowired
    private ComponentService componentService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private RiskService riskService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private OptionValMapper optionValMapper;

    @Override
    public Integer cloneProject(Integer projectId, String newPrjName, String newPrjKey, Integer sAccountId, String username) {
        SimpleProject project = projectService.findById(projectId, sAccountId);
        if (project != null) {
            LOG.info("Clone project info");
            project.setId(null);
            project.setName(newPrjName);
            project.setShortname(newPrjKey);
            Integer newProjectId = projectService.savePlainProject(project, username);
            cloneProjectOptions(projectId, newProjectId, sAccountId);
            Map<Integer, Integer> mapRoleIds = cloneProjectRoles(projectId, newProjectId, username, sAccountId);
            cloneProjectMembers(projectId, newProjectId, mapRoleIds, username);
            cloneProjectMessages(projectId, newProjectId, username);
            cloneProjectRisks(projectId, newProjectId, username);
            Map<Integer, Integer> milestoneMapIds = cloneProjectMilestone(projectId, newProjectId, username);
            cloneProjectTasks(projectId, newProjectId, milestoneMapIds, username);
            Map<Integer, Integer> componentMapIds = cloneProjectComponents(projectId, newProjectId, username, sAccountId);
            Map<Integer, Integer> versionMapIds = cloneProjectVersions(projectId, newProjectId, username, sAccountId);
            cloneProjectBugs(projectId, newProjectId, milestoneMapIds, componentMapIds, versionMapIds, username, sAccountId);

            return newProjectId;
        } else {
            throw new MyCollabException("Can not find project with id " + projectId);
        }
    }

    private void cloneProjectOptions(Integer projectId, Integer newProjectId, Integer sAccountId) {
        OptionValExample ex = new OptionValExample();
        ex.createCriteria().andIsdefaultEqualTo(false).andSaccountidEqualTo(sAccountId).andExtraidEqualTo(projectId);
        List<OptionVal> optionVals = optionValMapper.selectByExample(ex);
        for (OptionVal optionVal : optionVals) {
            optionVal.setId(null);
            optionVal.setExtraid(newProjectId);
            optionValMapper.insert(optionVal);
        }
    }

    private Map<Integer, Integer> cloneProjectRoles(Integer projectId, Integer newProjectId, String username, Integer sAccountId) {
        LOG.info("Clone project roles");
        Map<Integer, Integer> mapRoleIds = new HashMap<>();
        ProjectRoleSearchCriteria searchCriteria = new ProjectRoleSearchCriteria();
        searchCriteria.setProjectId(new NumberSearchField(projectId));
        List<SimpleProjectRole> roles = projectRoleService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (SimpleProjectRole role : roles) {
            role.setId(null);
            role.setProjectid(newProjectId);
            Integer newRoleId = projectRoleService.saveWithSession(role, username);
            projectRoleService.savePermission(projectId, newRoleId, role.getPermissionMap(), sAccountId);
            mapRoleIds.put(role.getId(), newRoleId);
        }
        return mapRoleIds;
    }

    private void cloneProjectTasks(Integer projectId, Integer newProjectId, Map<Integer, Integer> milestoneMapIds, String username) {
        LOG.info("Clone project tasks");
        Map<Integer, Integer> taskMapIds = new HashMap<>();
        TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
        searchCriteria.setProjectId(NumberSearchField.and(projectId));
        List<SimpleTask> tasks = projectTaskService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        cloneProjectTasks(newProjectId, milestoneMapIds, taskMapIds, tasks, username);
    }

    private void cloneProjectTasks(Integer newProjectId, Map<Integer, Integer> milestoneMapIds,
                                   Map<Integer, Integer> taskMapIds, List<SimpleTask> tasks, String username) {
        List<SimpleTask> pendingTasks = new ArrayList<>();
        for (SimpleTask task : tasks) {
            Integer taskId = task.getId();
            Integer parentTaskId = task.getParenttaskid();
            if (parentTaskId == null) {
                task.setId(null);
                task.setMilestoneid(milestoneMapIds.get(task.getMilestoneid()));
                task.setProjectid(newProjectId);
                Integer newTaskId = projectTaskService.saveWithSession(task, username);
                taskMapIds.put(taskId, newTaskId);
            } else {
                Integer candidateParentTaskId = taskMapIds.get(parentTaskId);
                if (candidateParentTaskId != null) {
                    task.setId(null);
                    task.setProjectid(newProjectId);
                    task.setMilestoneid(milestoneMapIds.get(task.getMilestoneid()));
                    task.setParenttaskid(candidateParentTaskId);
                    Integer newTaskId = projectTaskService.saveWithSession(task, username);
                    taskMapIds.put(taskId, newTaskId);
                } else {
                    pendingTasks.add(task);
                }
            }
        }
        if (pendingTasks.size() > 0) {
            cloneProjectTasks(newProjectId, milestoneMapIds, taskMapIds, pendingTasks, username);
        }
    }

    private Map<Integer, Integer> cloneProjectVersions(Integer projectId, Integer newProjectId, String username, Integer sAccountId) {
        LOG.info("Clone project versions");
        Map<Integer, Integer> versionMapIds = new HashMap<>();
        VersionSearchCriteria searchCriteria = new VersionSearchCriteria();
        searchCriteria.setProjectId(NumberSearchField.and(projectId));
        List<SimpleVersion> versions = versionService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (SimpleVersion version : versions) {
            Integer versionId = version.getId();
            version.setId(null);
            version.setProjectid(newProjectId);
            Integer newVersionId = versionService.saveWithSession(version, username);
            versionMapIds.put(versionId, newVersionId);
        }
        return versionMapIds;
    }

    private Map<Integer, Integer> cloneProjectComponents(Integer projectId, Integer newProjectId, String username, Integer sAccountId) {
        LOG.info("Clone project components");
        Map<Integer, Integer> componentMapIds = new HashMap<>();
        ComponentSearchCriteria searchCriteria = new ComponentSearchCriteria();
        searchCriteria.setProjectId(NumberSearchField.and(projectId));
        List<SimpleComponent> components = componentService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (SimpleComponent component : components) {
            Integer componentId = component.getId();
            component.setId(null);
            component.setProjectid(newProjectId);
            Integer newComponentId = componentService.saveWithSession(component, username);
            componentMapIds.put(componentId, newComponentId);
        }
        return componentMapIds;
    }

    private void cloneProjectBugs(Integer projectId, Integer newProjectId, Map<Integer, Integer> milestoneMapIds,
                                  Map<Integer, Integer> componentMapIds, Map<Integer, Integer> versionMapIds,
                                  String username, Integer sAccountId) {
        LOG.info("Clone project bugs");
        BugSearchCriteria searchCriteria = new BugSearchCriteria();
        searchCriteria.setProjectId(NumberSearchField.and(projectId));
        List<SimpleBug> bugs = bugService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (SimpleBug bug : bugs) {
            bug.setId(null);
            bug.setProjectid(newProjectId);
            bug.setMilestoneid(milestoneMapIds.get(bug.getMilestoneid()));
            Integer newBugId = bugService.saveWithSession(bug, username);

            List<Version> affectedVersions = bug.getAffectedVersions();
            for (Version version : affectedVersions) {
                BugRelatedItem bugRelatedItem = new BugRelatedItem();
                bugRelatedItem.setBugid(newBugId);
                bugRelatedItem.setType(SimpleRelatedBug.AFFVERSION);
                bugRelatedItem.setTypeid(versionMapIds.get(version.getId()));
                bugRelatedItemMapper.insert(bugRelatedItem);
            }

            List<Version> fixedVersions = bug.getFixedVersions();
            for (Version version : fixedVersions) {
                BugRelatedItem bugRelatedItem = new BugRelatedItem();
                bugRelatedItem.setBugid(newBugId);
                bugRelatedItem.setType(SimpleRelatedBug.FIXVERSION);
                bugRelatedItem.setTypeid(versionMapIds.get(version.getId()));
                bugRelatedItemMapper.insert(bugRelatedItem);
            }

            List<Component> components = bug.getComponents();
            for (Component component : components) {
                BugRelatedItem bugRelatedItem = new BugRelatedItem();
                bugRelatedItem.setBugid(newBugId);
                bugRelatedItem.setType(SimpleRelatedBug.COMPONENT);
                bugRelatedItem.setTypeid(componentMapIds.get(component.getId()));
                bugRelatedItemMapper.insert(bugRelatedItem);
            }
        }
    }

    private void cloneProjectMembers(Integer projectId, Integer newProjectId, Map<Integer, Integer> mapRoleIds, String username) {
        LOG.info("Clone project members");
        ProjectMemberSearchCriteria searchCriteria = new ProjectMemberSearchCriteria();
        searchCriteria.setProjectId(new NumberSearchField(projectId));
        searchCriteria.setStatuses(new SetSearchField<>(ProjectMemberStatusConstants.ACTIVE,
                ProjectMemberStatusConstants.NOT_ACCESS_YET));
        List<SimpleProjectMember> members = projectMemberService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (SimpleProjectMember member : members) {
            member.setId(null);
            member.setProjectid(newProjectId);
            if (Boolean.FALSE.equals(member.getIsadmin())) {
                Integer newRoleId = mapRoleIds.get(member.getProjectroleid());
                member.setProjectroleid(newRoleId);
            }
            projectMemberService.saveWithSession(member, username);
        }
    }

    private void cloneProjectMessages(Integer projectId, Integer newProjectId, String username) {
        LOG.info("Clone project messages");
        MessageSearchCriteria searchCriteria = new MessageSearchCriteria();
        searchCriteria.setProjectids(new SetSearchField<>(projectId));
        List<SimpleMessage> messages = messageService.findPageableListByCriteria(new BasicSearchRequest<>
                (searchCriteria, 0, Integer.MAX_VALUE));
        for (SimpleMessage message : messages) {
            message.setId(null);
            message.setProjectid(newProjectId);
            messageService.saveWithSession(message, username);
        }
    }

    private void cloneProjectRisks(Integer projectId, Integer newProjectId, String username) {
        LOG.info("Clone project risks");
        RiskSearchCriteria searchCriteria = new RiskSearchCriteria();
        searchCriteria.setProjectId(NumberSearchField.and(projectId));
        List<SimpleRisk> risks = riskService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (SimpleRisk risk : risks) {
            risk.setId(null);
            risk.setProjectid(newProjectId);
            riskService.saveWithSession(risk, username);
        }
    }

    private Map<Integer, Integer> cloneProjectMilestone(Integer projectId, Integer newProjectId, String username) {
        LOG.info("Clone project milestones");
        Map<Integer, Integer> milestoneMapIds = new HashMap<>();
        MilestoneSearchCriteria searchCriteria = new MilestoneSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(projectId));
        List<SimpleMilestone> milestones = milestoneService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (SimpleMilestone milestone : milestones) {
            Integer milestoneId = milestone.getId();
            milestone.setId(null);
            milestone.setProjectid(newProjectId);
            Integer newMilestoneId = milestoneService.saveWithSession(milestone, username);
            milestoneMapIds.put(milestoneId, newMilestoneId);
        }
        return milestoneMapIds;
    }
}
