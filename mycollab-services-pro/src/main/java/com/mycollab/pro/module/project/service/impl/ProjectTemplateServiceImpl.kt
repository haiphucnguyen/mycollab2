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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author myCollab Ltd
 * @since 5.2.8
 */
@Service
class ProjectTemplateServiceImpl : ProjectTemplateService {

    @Autowired
    private val projectService: ProjectService? = null

    @Autowired
    private val projectRoleService: ProjectRoleService? = null

    @Autowired
    private val projectMemberService: ProjectMemberService? = null

    @Autowired
    private val projectTaskService: ProjectTaskService? = null

    @Autowired
    private val bugService: BugService? = null

    @Autowired
    private val bugRelatedItemMapper: BugRelatedItemMapper? = null

    @Autowired
    private val milestoneService: MilestoneService? = null

    @Autowired
    private val componentService: ComponentService? = null

    @Autowired
    private val versionService: VersionService? = null

    @Autowired
    private val riskService: RiskService? = null

    @Autowired
    private val messageService: MessageService? = null

    @Autowired
    private val optionValMapper: OptionValMapper? = null

    override fun cloneProject(projectId: Int, newPrjName: String, newPrjKey: String, sAccountId: Int?, username: String): Int? {
        val project = projectService!!.findById(projectId, sAccountId)
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
            throw MyCollabException("Can not find project with id " + projectId!!)
        }
    }

    private fun cloneProjectOptions(projectId: Int?, newProjectId: Int?, sAccountId: Int?) {
        val ex = OptionValExample()
        ex.createCriteria().andIsdefaultEqualTo(false).andSaccountidEqualTo(sAccountId).andExtraidEqualTo(projectId)
        val optionVals = optionValMapper!!.selectByExample(ex)
        for (optionVal in optionVals) {
            optionVal.id = null
            optionVal.extraid = newProjectId
            optionValMapper.insert(optionVal)
        }
    }

    private fun cloneProjectRoles(projectId: Int?, newProjectId: Int?, username: String, sAccountId: Int?): Map<Int, Int> {
        LOG.info("Clone project roles")
        val mapRoleIds = HashMap<Int, Int>()
        val searchCriteria = ProjectRoleSearchCriteria()
        searchCriteria.projectId = NumberSearchField(projectId)
        val roles = projectRoleService!!.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleProjectRole>
        for (role in roles) {
            role.id = null
            role.projectid = newProjectId
            val newRoleId = projectRoleService.saveWithSession(role, username)
            projectRoleService.savePermission(projectId, newRoleId, role.getPermissionMap(), sAccountId)
            mapRoleIds.put(role.getId(), newRoleId)
        }
        return mapRoleIds
    }

    private fun cloneProjectTasks(projectId: Int, newProjectId: Int, milestoneMapIds: Map<Int, Int>, username: String) {
        LOG.info("Clone project tasks")
        val taskMapIds = HashMap<Int, Int>()
        val searchCriteria = TaskSearchCriteria()
        searchCriteria.projectId = NumberSearchField.equal(projectId)
        val tasks = projectTaskService!!.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleTask>
        cloneProjectTasks(newProjectId, milestoneMapIds, taskMapIds, tasks, username)
    }

    private fun cloneProjectTasks(newProjectId: Int?, milestoneMapIds: Map<Int, Int>,
                                  taskMapIds: MutableMap<Int, Int>, tasks: List<SimpleTask>, username: String) {
        val pendingTasks = ArrayList<SimpleTask>()
        for (task in tasks) {
            val taskId = task.id
            val parentTaskId = task.parenttaskid
            if (parentTaskId == null) {
                task.id = null
                task.milestoneid = milestoneMapIds[task.milestoneid]
                task.projectid = newProjectId
                val newTaskId = projectTaskService!!.saveWithSession(task, username)
                taskMapIds.put(taskId, newTaskId)
            } else {
                val candidateParentTaskId = taskMapIds[parentTaskId]
                if (candidateParentTaskId != null) {
                    task.id = null
                    task.projectid = newProjectId
                    task.milestoneid = milestoneMapIds[task.milestoneid]
                    task.parenttaskid = candidateParentTaskId
                    val newTaskId = projectTaskService!!.saveWithSession(task, username)
                    taskMapIds.put(taskId, newTaskId)
                } else {
                    pendingTasks.add(task)
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
        val versions = versionService!!.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleVersion>
        versions.forEach { version ->
            val versionId = version.id
            version.id = null
            version.projectid = newProjectId
            val newVersionId = versionService.saveWithSession(version, username)
            versionMapIds.put(versionId, newVersionId)
        }
        return versionMapIds
    }

    private fun cloneProjectComponents(projectId: Int, newProjectId: Int, username: String): Map<Int, Int> {
        LOG.info("Clone project components")
        val componentMapIds = HashMap<Int, Int>()
        val searchCriteria = ComponentSearchCriteria()
        searchCriteria.projectId = NumberSearchField.equal(projectId)
        val components = componentService!!.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleComponent>
        components.forEach { component ->
            val componentId = component.id
            component.id = null
            component.projectid = newProjectId
            val newComponentId = componentService.saveWithSession(component, username)
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
        val bugs = bugService!!.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleBug>
        bugs.forEach { bug ->
            bug.id = null
            bug.projectid = newProjectId
            bug.milestoneid = milestoneMapIds[bug.getMilestoneid()]
            val newBugId = bugService.saveWithSession(bug, username)

            val affectedVersions = bug.getAffectedVersions()
            affectedVersions.forEach { version ->
                val bugRelatedItem = BugRelatedItem()
                bugRelatedItem.bugid = newBugId
                bugRelatedItem.type = SimpleRelatedBug.AFFVERSION
                bugRelatedItem.typeid = versionMapIds[version.getId()]
                bugRelatedItemMapper!!.insert(bugRelatedItem)
            }

            val fixedVersions = bug.getFixedVersions()
            fixedVersions.forEach { version ->
                val bugRelatedItem = BugRelatedItem()
                bugRelatedItem.bugid = newBugId
                bugRelatedItem.type = SimpleRelatedBug.FIXVERSION
                bugRelatedItem.typeid = versionMapIds[version.getId()]
                bugRelatedItemMapper!!.insert(bugRelatedItem)
            }

            val components = bug.getComponents()
            components.forEach { component ->
                val bugRelatedItem = BugRelatedItem()
                bugRelatedItem.bugid = newBugId
                bugRelatedItem.type = SimpleRelatedBug.COMPONENT
                bugRelatedItem.typeid = componentMapIds[component.getId()]
                bugRelatedItemMapper!!.insert(bugRelatedItem)
            }
        }
    }

    private fun cloneProjectMembers(projectId: Int?, newProjectId: Int?, mapRoleIds: Map<Int, Int>, username: String) {
        LOG.info("Clone project members")
        val searchCriteria = ProjectMemberSearchCriteria()
        searchCriteria.projectId = NumberSearchField(projectId)
        searchCriteria.statuses = SetSearchField(ProjectMemberStatusConstants.ACTIVE,
                ProjectMemberStatusConstants.NOT_ACCESS_YET)
        val members = projectMemberService!!.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleProjectMember>
        members.forEach { member ->
            member.id = null
            member.projectid = newProjectId
            if (java.lang.Boolean.FALSE == member.isadmin) {
                val newRoleId = mapRoleIds[member.projectroleid]
                member.projectroleid = newRoleId
            }
            projectMemberService.saveWithSession(member, username)
        }
    }

    private fun cloneProjectMessages(projectId: Int?, newProjectId: Int?, username: String) {
        LOG.info("Clone project messages")
        val searchCriteria = MessageSearchCriteria()
        searchCriteria.projectids = SetSearchField(projectId)
        val messages = messageService!!.findPageableListByCriteria(BasicSearchRequest(searchCriteria, 0, Integer.MAX_VALUE)) as List<SimpleMessage>
        messages.forEach { message ->
            message.id = null
            message.projectid = newProjectId
            messageService.saveWithSession(message, username)
        }
    }

    private fun cloneProjectRisks(projectId: Int, newProjectId: Int, username: String) {
        LOG.info("Clone project risks")
        val searchCriteria = RiskSearchCriteria()
        searchCriteria.projectId = NumberSearchField.equal(projectId)
        val risks = riskService!!.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleRisk>
        risks.forEach { risk ->
            risk.id = null
            risk.projectid = newProjectId
            riskService.saveWithSession(risk, username)
        }
    }

    private fun cloneProjectMilestone(projectId: Int?, newProjectId: Int?, username: String): Map<Int, Int> {
        LOG.info("Clone project milestones")
        val milestoneMapIds = HashMap<Int, Int>()
        val searchCriteria = MilestoneSearchCriteria()
        searchCriteria.projectIds = SetSearchField(projectId)
        val milestones = milestoneService!!.findPageableListByCriteria(BasicSearchRequest(searchCriteria)) as List<SimpleMilestone>
        milestones.forEach { milestone ->
            val milestoneId = milestone.id
            milestone.id = null
            milestone.projectid = newProjectId
            val newMilestoneId = milestoneService.saveWithSession(milestone, username)
            milestoneMapIds.put(milestoneId, newMilestoneId)
        }
        return milestoneMapIds
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ProjectTemplateService::class.java)
    }
}
