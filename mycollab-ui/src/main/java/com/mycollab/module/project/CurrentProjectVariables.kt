package com.mycollab.module.project

import com.google.common.base.MoreObjects
import com.google.common.eventbus.AsyncEventBus
import com.mycollab.core.SecureAccessException
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.module.file.PathUtils
import com.mycollab.module.project.dao.ProjectRolePermissionMapper
import com.mycollab.module.project.domain.*
import com.mycollab.module.project.esb.NewProjectMemberJoinEvent
import com.mycollab.module.project.service.ProjectMemberService
import com.mycollab.security.PermissionMap
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.AppUI
import com.mycollab.vaadin.UserUIContext
import com.mycollab.vaadin.ui.MyCollabSession
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.mycollab.vaadin.ui.MyCollabSession.CURRENT_PROJECT
import com.mycollab.vaadin.ui.MyCollabSession.PROJECT_MEMBER

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
object CurrentProjectVariables {
    private val LOG = LoggerFactory.getLogger(CurrentProjectVariables::class.java)

    private val CURRENT_PAGE_VAR = "project_page"
    private val TOGGLE_MENU_FLAG = "toogleProjectMenu"

    // get member permission
    @JvmStatic var project: SimpleProject?
        get() = MyCollabSession.getCurrentUIVariable(CURRENT_PROJECT) as SimpleProject
        set(project) {
            MyCollabSession.putCurrentUIVariable(CURRENT_PROJECT, project)
            val prjMemberService = AppContextUtil.getSpringBean(ProjectMemberService::class.java)
            val prjMember = prjMemberService.findMemberByUsername(UserUIContext.getUsername(), project!!.id, AppUI.accountId)
            if (prjMember != null) {
                if (ProjectMemberStatusConstants.INACTIVE == prjMember.status) {
                    throw UserNotBelongProjectException("You are not belong to this project")
                }
                if (!prjMember.isProjectOwner) {
                    if (prjMember.projectroleid == null) {
                        throw UserNotBelongProjectException("You are not belong to this project")
                    }
                    val ex = ProjectRolePermissionExample()
                    ex.createCriteria().andRoleidEqualTo(prjMember.projectroleid).andProjectidEqualTo(CurrentProjectVariables.projectId)
                    val rolePermissionMapper = AppContextUtil.getSpringBean(ProjectRolePermissionMapper::class.java)
                    val rolePermissions = rolePermissionMapper.selectByExampleWithBLOBs(ex)
                    if (!rolePermissions.isEmpty()) {
                        val rolePer = rolePermissions[0]
                        val permissionMap = PermissionMap.fromJsonString(rolePer.roleval)
                        prjMember.permissionMaps = permissionMap
                    }
                }

                if (ProjectMemberStatusConstants.NOT_ACCESS_YET == prjMember.status) {
                    prjMember.status = ProjectMemberStatusConstants.ACTIVE
                    prjMemberService.updateSelectiveWithSession(prjMember, UserUIContext.getUsername())
                    val asyncEventBus = AppContextUtil.getSpringBean(AsyncEventBus::class.java)
                    asyncEventBus.post(NewProjectMemberJoinEvent(prjMember.username, prjMember.projectid, AppUI.accountId))
                }
                projectMember = prjMember
                if (projectToggleMenu == null) {
                    projectToggleMenu = true
                }
            } else if (!UserUIContext.isAdmin()) {
                throw SecureAccessException("You are not belong to this project")
            }
        }

    var projectToggleMenu: Boolean?
        get() = MyCollabSession.getCurrentUIVariable(TOGGLE_MENU_FLAG) as Boolean
        set(visibility) = MyCollabSession.putCurrentUIVariable(TOGGLE_MENU_FLAG, visibility)

    private var projectMember: SimpleProjectMember?
        get() = MyCollabSession.getCurrentUIVariable(PROJECT_MEMBER) as SimpleProjectMember
        set(prjMember) = MyCollabSession.putCurrentUIVariable(PROJECT_MEMBER, prjMember)

    val isAdmin: Boolean
        get() {
            if (UserUIContext.isAdmin()) {
                return true
            }
            val member = projectMember
            return member != null && member.isProjectOwner
        }

    val isProjectArchived: Boolean
        get() = project!!.isProjectArchived

    fun canRead(permissionItem: String): Boolean {
        if (isAdmin) {
            return true
        }

        try {
            val permissionMap = projectMember!!.permissionMaps
            return permissionMap != null && permissionMap.canRead(permissionItem)
        } catch (e: Exception) {
            LOG.error("Error while checking permission", e)
            return false
        }

    }

    fun canReadAssignments(): Boolean {
        return canRead(ProjectRolePermissionCollections.BUGS) || canRead(ProjectRolePermissionCollections.TASKS) ||
                canRead(ProjectRolePermissionCollections.RISKS) || canRead(ProjectRolePermissionCollections.MILESTONES)
    }

    @JvmStatic fun canWrite(permissionItem: String): Boolean {
        if (isProjectArchived) {
            return false
        }

        if (isAdmin) {
            return true
        }

        try {
            val permissionMap = projectMember!!.permissionMaps
            return permissionMap != null && permissionMap.canWrite(permissionItem)
        } catch (e: Exception) {
            LOG.error("Error while checking permission", e)
            return false
        }

    }

    fun canAccess(permissionItem: String): Boolean {
        if (isProjectArchived) {
            return false
        }

        if (isAdmin) {
            return true
        }

        try {
            val permissionMap = projectMember!!.permissionMaps
            return permissionMap != null && permissionMap.canAccess(permissionItem)
        } catch (e: Exception) {
            LOG.error("Error while checking permission", e)
            return false
        }

    }

    val features: ProjectCustomizeView
        get() {
            var customizeView: ProjectCustomizeView? = project!!.customizeView
            if (customizeView == null) {
                customizeView = ProjectCustomizeView()
                customizeView.projectid = CurrentProjectVariables.projectId
                customizeView.displayticket = true
                customizeView.displaymessage = true
                customizeView.displaymilestone = true
                customizeView.displaypage = true
                customizeView.displaystandup = true
                customizeView.displaytimelogging = true
                customizeView.displayfile = true
                customizeView.displayinvoice = true
            }
            return customizeView
        }

    fun hasMessageFeature(): Boolean {
        return features.displaymessage!!
    }

    fun hasPhaseFeature(): Boolean {
        return features.displaymilestone!!
    }

    fun hasTicketFeature(): Boolean {
        return MoreObjects.firstNonNull(features.displayticket, true)
    }

    fun hasPageFeature(): Boolean {
        return features.displaypage!!
    }

    fun hasFileFeature(): Boolean {
        return features.displayfile!!
    }

    fun hasTimeFeature(): Boolean {
        return features.displaytimelogging!!
    }

    fun hasInvoiceFeature(): Boolean {
        return java.lang.Boolean.TRUE == features.displayinvoice
    }

    fun hasStandupFeature(): Boolean {
        return features.displaystandup!!
    }

    @JvmStatic var currentPagePath: String
        get() {
            var path: String? = MyCollabSession.getCurrentUIVariable(CURRENT_PAGE_VAR) as String
            if (path == null) {
                path = PathUtils.getProjectDocumentPath(AppUI.accountId, projectId)
                currentPagePath = path
            }

            return path
        }
        set(path) = MyCollabSession.putCurrentUIVariable(CURRENT_PAGE_VAR, path)

    @JvmStatic val projectId: Int
        get() {
            val project = project
            return if (project != null) project.id else -1
        }

    val shortName: String
        get() {
            val project = project
            return if (project != null) project.shortname else ""
        }

    @JvmStatic fun canWriteTicket(ticket: ProjectTicket): Boolean {
        return when {
            ticket.isTask -> canWrite(ProjectRolePermissionCollections.TASKS)
            ticket.isBug -> canWrite(ProjectRolePermissionCollections.BUGS)
            ticket.isRisk -> canWrite(ProjectRolePermissionCollections.RISKS)
            else -> false
        }
    }

    @JvmStatic fun canReadTicket(): Boolean {
        return canRead(ProjectRolePermissionCollections.TASKS) || canRead(ProjectRolePermissionCollections.BUGS)
                || canRead(ProjectRolePermissionCollections.RISKS)
    }

    @JvmStatic fun canWriteTicket(): Boolean {
        return canWrite(ProjectRolePermissionCollections.TASKS) || canWrite(ProjectRolePermissionCollections.BUGS)
                || canWrite(ProjectRolePermissionCollections.RISKS)
    }

    @JvmStatic val restrictedItemTypes: SetSearchField<String>
        get() {
            val types = SetSearchField<String>()
            if (canRead(ProjectRolePermissionCollections.MESSAGES)) {
                types.addValue(ProjectTypeConstants.MESSAGE)
            }
            if (canRead(ProjectRolePermissionCollections.MILESTONES)) {
                types.addValue(ProjectTypeConstants.MILESTONE)
            }
            if (canRead(ProjectRolePermissionCollections.TASKS)) {
                types.addValue(ProjectTypeConstants.TASK)
            }
            if (canRead(ProjectRolePermissionCollections.BUGS)) {
                types.addValue(ProjectTypeConstants.BUG)
            }
            if (canRead(ProjectRolePermissionCollections.RISKS)) {
                types.addValue(ProjectTypeConstants.RISK)
            }
            if (canRead(ProjectRolePermissionCollections.COMPONENTS)) {
                types.addValue(ProjectTypeConstants.BUG_COMPONENT)
            }
            if (canRead(ProjectRolePermissionCollections.VERSIONS)) {
                types.addValue(ProjectTypeConstants.BUG_VERSION)
            }
            return types
        }

    @JvmStatic val restrictedTicketTypes: SetSearchField<String>
        get() {
            val types = SetSearchField<String>()
            if (canRead(ProjectRolePermissionCollections.TASKS)) {
                types.addValue(ProjectTypeConstants.TASK)
            }
            if (canRead(ProjectRolePermissionCollections.BUGS)) {
                types.addValue(ProjectTypeConstants.BUG)
            }
            if (canRead(ProjectRolePermissionCollections.RISKS)) {
                types.addValue(ProjectTypeConstants.RISK)
            }
            return types
        }
}
