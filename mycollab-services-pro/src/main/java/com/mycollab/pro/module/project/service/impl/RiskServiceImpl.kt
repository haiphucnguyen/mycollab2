package com.mycollab.pro.module.project.service.impl

import com.google.common.eventbus.AsyncEventBus
import com.mycollab.aspect.ClassInfo
import com.mycollab.aspect.ClassInfoMap
import com.mycollab.aspect.Traceable
import com.mycollab.cache.CleanCacheEvent
import com.mycollab.common.ModuleNameConstants
import com.mycollab.common.event.TimelineTrackingUpdateEvent
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.db.persistence.service.DefaultService
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.domain.Risk
import com.mycollab.module.project.domain.SimpleRisk
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria
import com.mycollab.module.project.esb.DeleteProjectRiskEvent
import com.mycollab.module.project.service.*
import com.mycollab.pro.module.project.dao.RiskMapper
import com.mycollab.pro.module.project.dao.RiskMapperExt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
@Transactional
@Traceable(nameField = "name", extraFieldName = "projectid")
class RiskServiceImpl(private val riskMapper: RiskMapper,
                      private val riskMapperExt: RiskMapperExt,
                      private val asyncEventBus: AsyncEventBus) : DefaultService<Int, Risk, RiskSearchCriteria>(), RiskService {

    override val crudMapper: ICrudGenericDAO<Int, Risk>
        get() = riskMapper as ICrudGenericDAO<Int, Risk>

    override val searchMapper: ISearchableDAO<RiskSearchCriteria>
        get() = riskMapperExt

    override fun findById(riskId: Int, sAccountId: Int): SimpleRisk? =
            riskMapperExt.findRiskById(riskId)

    override fun saveWithSession(record: Risk, username: String?): Int {
        val recordId = super.saveWithSession(record, username)
        asyncEventBus.post(CleanCacheEvent(record.saccountid, arrayOf(ProjectService::class.java, ProjectTicketService::class.java, ProjectActivityStreamService::class.java)))
        return recordId
    }

    override fun updateSelectiveWithSession(record: Risk, username: String?): Int? {
        cleanAfterUpdate(record)
        return super.updateSelectiveWithSession(record, username)
    }

    override fun updateWithSession(record: Risk, username: String?): Int {
        cleanAfterUpdate(record)
        return super.updateWithSession(record, username)
    }

    private fun cleanAfterUpdate(record: Risk) {
        asyncEventBus.post(CleanCacheEvent(record.saccountid, arrayOf<Class<*>>(ProjectService::class.java, ProjectTicketService::class.java, ProjectActivityStreamService::class.java, ProjectTicketService::class.java)))
        asyncEventBus.post(TimelineTrackingUpdateEvent(ProjectTypeConstants.RISK, record.id, "status",
                record.status, record.projectid, record.saccountid))
    }

    override fun removeByCriteria(criteria: RiskSearchCriteria, sAccountId: Int) {
        super.removeByCriteria(criteria, sAccountId)
        asyncEventBus.post(CleanCacheEvent(sAccountId, arrayOf<Class<*>>(ProjectService::class.java, ProjectTicketService::class.java, ProjectActivityStreamService::class.java, ItemTimeLoggingService::class.java)))
    }

    override fun massRemoveWithSession(items: List<Risk>, username: String?, sAccountId: Int) {
        super.massRemoveWithSession(items, username, sAccountId)
        asyncEventBus.post(CleanCacheEvent(sAccountId, arrayOf<Class<*>>(ProjectService::class.java, ProjectTicketService::class.java, ProjectActivityStreamService::class.java, ItemTimeLoggingService::class.java)))
        val event = DeleteProjectRiskEvent(items.toTypedArray(), username, sAccountId)
        asyncEventBus.post(event)
    }

    companion object {

        init {
            ClassInfoMap.put(RiskServiceImpl::class.java, ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.RISK))
        }
    }
}
