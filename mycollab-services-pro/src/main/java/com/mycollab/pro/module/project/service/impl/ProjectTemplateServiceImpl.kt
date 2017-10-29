package com.mycollab.pro.module.project.service.impl

import com.mycollab.common.dao.OptionValMapper
import com.mycollab.common.domain.OptionValExample
import com.mycollab.core.MyCollabException
import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.module.project.ProjectMemberStatusConstants
import com.mycollab.module.project.domain.*
import com.mycollab.module.project.domain.criteria.*
import com.mycollab.module.project.service.*
import com.mycollab.module.tracker.dao.BugRelatedItemMapper
import com.mycollab.module.tracker.domain.*
import com.mycollab.module.tracker.domain.criteria.BugSearchCriteria
import com.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria
import com.mycollab.module.tracker.domain.criteria.VersionSearchCriteria
import com.mycollab.module.tracker.service.BugService
import com.mycollab.module.tracker.service.ComponentService
import com.mycollab.module.tracker.service.VersionService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author myCollab Ltd
 * @since 5.2.8
 */
@Service
class ProjectTemplateServiceImpl(private val projectService: ProjectService,
                                 private val projectRoleService: ProjectRoleService,
                                 private val projectMemberService: ProjectMemberService,
                                 private val projectTaskService: ProjectTaskService,
                                 private val bugService: BugService,
                                 private val bugRelatedItemMapper: BugRelatedItemMapper,
                                 private val milestoneService: MilestoneService,
                                 private val componentService: ComponentService,
                                 private val versionService: VersionService,
                                 private val riskService: RiskService,
                                 private val messageService: MessageService,
                                 private val optionValMapper: OptionValMapper) : ProjectTemplateService {

    override fun cloneProject(projectId: Int, newPrjName: String, newPrjKey: String, sAccountId: Int, username: String): Int? {
        val project = projectService.findById(projectId, sAccountId)
        if (project != null) {
            LOG.info("Clone project info")
            project.id = null
            project.name = newPrjName
            project.shortname = newPrjKey
            val newProjectId = projectService.savePlainProject(project, username)
            cloneProjectOptions(projectId, newProjectId, sAccountId)
            val mapRoleIds = cloneProjectRoles(projectId, newProjectId, username, sAccountId)
            cloneProjectMembers(projectId, newProjectId, mapRoleIds, username)
            cloneProjectMessages(projectId, newProjectId, username)
            cloneProjectRisks(projectId, newProjectId, username)
            val milestoneMapIds = cloneProjectMilestone(projectId, newProjectId, username)
            cloneProjectTasks(projectId, newProjectId, milestoneMapIds, username)
            val componentMapIds = cloneProjectComponents(projectId, newProjectId, username)
            val versionMapIds = cloneProjectVersions(projectId, newProjectId, username)
            cloneProjectBugs(projectId, newProjectId, milestoneMapIds, componentMapIds, versionMapIds, username)

            return newProjectId
        } else {
            throw MyCollabException("Can not find project with id $projectId")
        }
    }

    private fun cloneProjectOptions(projectId: Int?, newProjectId: Int?, sAccountId: Int?) {
        val ex = OptionValExample()
        ex.createCriteria().andIsdefaultEqualTo(false).andSaccountidEqualTo(sAccountId).andExtraidEqualTo(projectId)
        val optionVals = optionValMapper.selectByExample(ex)
        optionVals.forEach {
            it.id = null
            it.extraid = newProjectId
            optionValMapper.insert(it)
        }
    }

    private fun cloneProjectRoles(projectId: Int, newProjectId: Int?, username: String, sAccountId: Int): Map<Int, Int> {
        LOG.info("Clone project roles")
        val mapRoleIds = mutableMapOf<Int, Int>()
        val searchCriteria = ProjectRoleSearchCriteria()
        searchCriteria.projectId = NumberSearchField(projectId)
        val roles = projectRoleService.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleProjectRole>
        roles.forEach {
            it.id = null
            it.projectid = newProjectId
            val newRoleId = projectRoleService.saveWithSession(it, username)
            projectRoleService.savePermission(projectId, newRoleId, it.permissionMap, sAccountId)
            mapRoleIds.put(it.id, newRoleId)
        }
        return mapRoleIds
    }

    private fun cloneProjectTasks(projectId: Int, newProjectId: Int, milestoneMapIds: Map<Int, Int>, username: String) {
        LOG.info("Clone project tasks")
        val taskMapIds = HashMap<Int, Int>()
        val searchCriteria = TaskSearchCriteria()
        searchCriteria.projectId = NumberSearchField.equal(projectId)
        val tasks = projectTaskService.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleTask>
        cloneProjectTasks(newProjectId, milestoneMapIds, taskMapIds, tasks, username)
    }

    private fun cloneProjectTasks(newProjectId: Int?, milestoneMapIds: Map<Int, Int>,
                                  taskMapIds: MutableMap<Int, Int>, tasks: List<SimpleTask>, username: String) {
        val pendingTasks = ArrayList<SimpleTask>()
        tasks.forEach {
            val taskId = it.id
            val parentTaskId = it.parenttaskid
            if (parentTaskId == null) {
                it.id = null
                it.milestoneid = milestoneMapIds[it.milestoneid]
                it.projectid = newProjectId
                val newTaskId = projectTaskService.saveWithSession(it, username)
                taskMapIds.put(taskId, newTaskId)
            } else {
                val candidateParentTaskId = taskMapIds[parentTaskId]
                if (candidateParentTaskId != null) {
                    it.id = null
                    it.projectid = newProjectId
                    it.milestoneid = milestoneMapIds[it.milestoneid]
                    it.parenttaskid = candidateParentTaskId
                    val newTaskId = projectTaskService.saveWithSession(it, username)
                    taskMapIds.put(taskId, newTaskId)
                } else {
                    pendingTasks.add(it)
                }
            }
        }
        if (pendingTasks.size > 0) {
            cloneProjectTasks(newProjectId, milestoneMapIds, taskMapIds, pendingTasks, username)
        }
    }

    private fun cloneProjectVersions(projectId: Int, newProjectId: Int, username: String): Map<Int, Int> {
        LOG.info("Clone project versions")
        val versionMapIds = HashMap<Int, Int>()
        val searchCriteria = VersionSearchCriteria()
        searchCriteria.projectId = NumberSearchField.equal(projectId)
        val versions = versionService.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleVersion>
        versions.forEach {
            val versionId = it.id
            it.id = null
            it.projectid = newProjectId
            val newVersionId = versionService.saveWithSession(it, username)
            versionMapIds.put(versionId, newVersionId)
        }
        return versionMapIds
    }

    private fun cloneProjectComponents(projectId: Int, newProjectId: Int, username: String): Map<Int, Int> {
        LOG.info("Clone project components")
        val componentMapIds = HashMap<Int, Int>()
        val searchCriteria = ComponentSearchCriteria()
        searchCriteria.projectId = NumberSearchField.equal(projectId)
        val components = componentService.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleComponent>
        components.forEach {
            val componentId = it.id
            it.id = null
            it.projectid = newProjectId
            val newComponentId = componentService.saveWithSession(it, username)
            componentMapIds.put(componentId, newComponentId)
        }
        return componentMapIds
    }

    private fun cloneProjectBugs(projectId: Int, newProjectId: Int, milestoneMapIds: Map<Int, Int>,
                                 componentMapIds: Map<Int, Int>, versionMapIds: Map<Int, Int>,
                                 username: String) {
        LOG.info("Clone project bugs")
        val searchCriteria = BugSearchCriteria()
        searchCriteria.projectId = NumberSearchField.equal(projectId)
        val bugs = bugService.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleBug>
        bugs.forEach {
            it.id = null
            it.projectid = newProjectId
            it.milestoneid = milestoneMapIds[it.milestoneid]
            val newBugId = bugService.saveWithSession(it, username)

            val affectedVersions = it.affectedVersions
            affectedVersions?.forEach {
                val bugRelatedItem = BugRelatedItem()
                bugRelatedItem.bugid = newBugId
                bugRelatedItem.type = SimpleRelatedBug.AFFVERSION
                bugRelatedItem.typeid = versionMapIds[it.id]
                bugRelatedItemMapper.insert(bugRelatedItem)
            }

            val fixedVersions = it.fixedVersions
            fixedVersions?.forEach {
                val bugRelatedItem = BugRelatedItem()
                bugRelatedItem.bugid = newBugId
                bugRelatedItem.type = SimpleRelatedBug.FIXVERSION
                bugRelatedItem.typeid = versionMapIds[it.id]
                bugRelatedItemMapper.insert(bugRelatedItem)
            }

            val components = it.components
            components?.forEach {
                val bugRelatedItem = BugRelatedItem()
                bugRelatedItem.bugid = newBugId
                bugRelatedItem.type = SimpleRelatedBug.COMPONENT
                bugRelatedItem.typeid = componentMapIds[it.id]
                bugRelatedItemMapper.insert(bugRelatedItem)
            }
        }
    }

    private fun cloneProjectMembers(projectId: Int, newProjectId: Int?, mapRoleIds: Map<Int, Int>, username: String) {
        LOG.info("Clone project members")
        val searchCriteria = ProjectMemberSearchCriteria()
        searchCriteria.projectIds = SetSearchField(projectId)
        searchCriteria.statuses = SetSearchField(ProjectMemberStatusConstants.ACTIVE,
                ProjectMemberStatusConstants.NOT_ACCESS_YET)
        val members = projectMemberService.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleProjectMember>
        members.forEach {
            it.id = null
            it.projectid = newProjectId
            if (java.lang.Boolean.FALSE == it.isadmin) {
                val newRoleId = mapRoleIds[it.projectroleid]
                it.projectroleid = newRoleId
            }
            projectMemberService.saveWithSession(it, username)
        }
    }

    private fun cloneProjectMessages(projectId: Int, newProjectId: Int, username: String) {
        LOG.info("Clone project messages")
        val searchCriteria = MessageSearchCriteria()
        searchCriteria.projectids = SetSearchField(projectId)
        val messages = messageService.findPageableListByCriteria(BasicSearchRequest(searchCriteria, 0, Integer.MAX_VALUE)) as List<SimpleMessage>
        messages.forEach {
            it.id = null
            it.projectid = newProjectId
            messageService.saveWithSession(it, username)
        }
    }

    private fun cloneProjectRisks(projectId: Int, newProjectId: Int, username: String) {
        LOG.info("Clone project risks")
        val searchCriteria = RiskSearchCriteria()
        searchCriteria.projectId = NumberSearchField.equal(projectId)
        val risks = riskService.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleRisk>
        risks.forEach {
            it.id = null
            it.projectid = newProjectId
            riskService.saveWithSession(it, username)
        }
    }

    private fun cloneProjectMilestone(projectId: Int, newProjectId: Int, username: String): Map<Int, Int> {
        LOG.info("Clone project milestones")
        val milestoneMapIds = HashMap<Int, Int>()
        val searchCriteria = MilestoneSearchCriteria()
        searchCriteria.projectIds = SetSearchField(projectId)
        val milestones = milestoneService.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleMilestone>
        milestones.forEach {
            val milestoneId = it.id
            it.id = null
            it.projectid = newProjectId
            val newMilestoneId = milestoneService.saveWithSession(it, username)
            milestoneMapIds.put(milestoneId, newMilestoneId)
        }
        return milestoneMapIds
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ProjectTemplateService::class.java)
    }
}
