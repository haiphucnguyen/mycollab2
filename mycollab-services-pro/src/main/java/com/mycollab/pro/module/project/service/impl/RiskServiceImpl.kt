package com.mycollab.pro.module.project.service.impl

import com.google.common.eventbus.AsyncEventBus
import com.mycollab.aspect.ClassInfo
import com.mycollab.aspect.ClassInfoMap
import com.mycollab.aspect.Traceable
import com.mycollab.cache.CleanCacheEvent
import com.mycollab.common.ModuleNameConstants
import com.mycollab.concurrent.DistributionLockUtil
import com.mycollab.core.MyCollabException
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
import java.util.concurrent.TimeUnit

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
@Transactional
@Traceable(nameField = "name", extraFieldName = "projectid")
class RiskServiceImpl(private val riskMapper: RiskMapper,
                      private val riskMapperExt: RiskMapperExt,
                      private val ticketKeyService: TicketKeyService,
                      private val asyncEventBus: AsyncEventBus) : DefaultService<Int, Risk, RiskSearchCriteria>(), RiskService {

    override val crudMapper: ICrudGenericDAO<Int, Risk>
        get() = riskMapper as ICrudGenericDAO<Int, Risk>

    override val searchMapper: ISearchableDAO<RiskSearchCriteria>
        get() = riskMapperExt

    override fun findById(riskId: Int, sAccountId: Int): SimpleRisk? =
            riskMapperExt.findRiskById(riskId)

    override fun saveWithSession(record: Risk, username: String?): Int {
        val lock = DistributionLockUtil.getLock("risk-${record.saccountid!!}")

        try {
            if (lock.tryLock(120, TimeUnit.SECONDS)) {
                val key = ticketKeyService.getMaxKey(record.projectid!!)
                val riskKey = if (key == null) 1 else key + 1

                val riskId =  super.saveWithSession(record, username)
                ticketKeyService.saveKey(record.projectid!!, riskId, ProjectTypeConstants.RISK, riskKey)
                asyncEventBus.post(CleanCacheEvent(record.saccountid, arrayOf(ProjectService::class.java, ProjectTicketService::class.java, ProjectActivityStreamService::class.java)))
                return riskId
            } else {
                throw MyCollabException("Timeout operation.")
            }
        } catch (e: InterruptedException) {
            throw MyCollabException(e)
        } finally {
            DistributionLockUtil.removeLock("risk-${record.saccountid!!}")
            lock.unlock()
        }
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
        asyncEventBus.post(CleanCacheEvent(record.saccountid, arrayOf(ProjectService::class.java, ProjectTicketService::class.java, ProjectActivityStreamService::class.java, ProjectTicketService::class.java)))
    }

    override fun removeByCriteria(criteria: RiskSearchCriteria, sAccountId: Int) {
        super.removeByCriteria(criteria, sAccountId)
        asyncEventBus.post(CleanCacheEvent(sAccountId, arrayOf(ProjectService::class.java, ProjectTicketService::class.java, ProjectActivityStreamService::class.java, ItemTimeLoggingService::class.java)))
    }

    override fun massRemoveWithSession(items: List<Risk>, username: String?, sAccountId: Int) {
        super.massRemoveWithSession(items, username, sAccountId)
        asyncEventBus.post(CleanCacheEvent(sAccountId, arrayOf(ProjectService::class.java, ProjectTicketService::class.java, ProjectActivityStreamService::class.java, ItemTimeLoggingService::class.java)))
        val event = DeleteProjectRiskEvent(items.toTypedArray(), username, sAccountId)
        asyncEventBus.post(event)
    }

    companion object {

        init {
            ClassInfoMap.put(RiskServiceImpl::class.java, ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.RISK))
        }
    }
}
