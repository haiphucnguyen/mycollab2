package com.mycollab.pro.module.project.service.impl

import com.google.common.eventbus.AsyncEventBus
import com.mycollab.cache.CleanCacheEvent
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum
import com.mycollab.core.MyCollabException
import com.mycollab.db.arguments.SearchField
import com.mycollab.module.project.domain.ProjectTicket
import com.mycollab.module.project.domain.Risk
import com.mycollab.module.project.domain.Task
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria
import com.mycollab.module.project.domain.criteria.TaskSearchCriteria
import com.mycollab.module.project.service.ProjectService
import com.mycollab.module.project.service.ProjectTaskService
import com.mycollab.module.project.service.RiskService
import com.mycollab.module.project.service.impl.AbstractProjectTicketServiceImpl
import com.mycollab.module.tracker.domain.BugWithBLOBs
import com.mycollab.module.tracker.domain.criteria.BugSearchCriteria
import com.mycollab.module.tracker.service.BugService
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@Service
class ProjectTicketServiceImpl(private val taskService: ProjectTaskService,
                               private val bugService: BugService,
                               private val riskService: RiskService,
                               private val asyncEventBus: AsyncEventBus) : AbstractProjectTicketServiceImpl() {

    override fun updateAssignmentValue(assignment: ProjectTicket, username: String) {
        when {
            assignment.isTask -> {
                val task = Task()
                task.name = assignment.name
                task.id = assignment.typeId
                task.milestoneid = assignment.milestoneId
                task.saccountid = assignment.sAccountId
                taskService.updateSelectiveWithSession(task, username)
            }
            assignment.isBug -> {
                val bug = BugWithBLOBs()
                bug.name = assignment.name
                bug.id = assignment.typeId
                bug.milestoneid = assignment.milestoneId
                bug.saccountid = assignment.sAccountId
                bugService.updateSelectiveWithSession(bug, username)
            }
            assignment.isRisk -> {
                val risk = Risk()
                risk.name = assignment.name
                risk.id = assignment.typeId
                risk.milestoneid = assignment.milestoneId
                risk.saccountid = assignment.sAccountId
                riskService.updateSelectiveWithSession(risk, username)
            }
            else -> throw MyCollabException("Not support")
        }

        asyncEventBus.post(CleanCacheEvent(assignment.sAccountId, arrayOf(ProjectService::class.java)))
    }

    override fun closeSubAssignmentOfMilestone(milestoneId: Int) {
        val bug = BugWithBLOBs()
        bug.status = StatusI18nEnum.Resolved.name
        val bugSearchCriteria = BugSearchCriteria()
        bugSearchCriteria.addExtraField(BugSearchCriteria.p_milestones.buildPropertyParamInList(SearchField.AND,
                setOf(milestoneId)))
        bugService.updateBySearchCriteria(bug, bugSearchCriteria)

        val risk = Risk()
        risk.status = StatusI18nEnum.Closed.name
        val riskSearchCriteria = RiskSearchCriteria()
        riskSearchCriteria.addExtraField(RiskSearchCriteria.p_milestones.buildPropertyParamInList(SearchField.AND,
                setOf(milestoneId)))
        riskService.updateBySearchCriteria(risk, riskSearchCriteria)

        val task = Task()
        task.status = StatusI18nEnum.Closed.name
        val taskSearchCriteria = TaskSearchCriteria()
        taskSearchCriteria.addExtraField(TaskSearchCriteria.p_milestoneId.buildPropertyParamInList(SearchField.AND,
                setOf(milestoneId)))
        taskService.updateBySearchCriteria(task, taskSearchCriteria)
    }
}
