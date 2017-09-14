package com.mycollab.module.crm.service.impl

import com.mycollab.cache.CleanCacheEvent
import com.mycollab.aspect.ClassInfo
import com.mycollab.aspect.ClassInfoMap
import com.mycollab.aspect.Traceable
import com.mycollab.aspect.Watchable
import com.mycollab.common.ModuleNameConstants
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.db.persistence.service.DefaultService
import com.mycollab.module.crm.CrmTypeConstants
import com.mycollab.module.crm.dao.CallMapper
import com.mycollab.module.crm.dao.CallMapperExt
import com.mycollab.module.crm.domain.CallWithBLOBs
import com.mycollab.module.crm.domain.SimpleCall
import com.mycollab.module.crm.domain.criteria.CallSearchCriteria
import com.mycollab.module.crm.service.CallService
import com.mycollab.module.crm.service.EventService
import com.google.common.eventbus.AsyncEventBus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
@Transactional
@Traceable(nameField = "subject")
@Watchable(userFieldName = "assignuser")
class CallServiceImpl : DefaultService<Int, CallWithBLOBs, CallSearchCriteria>(), CallService {

    @Autowired
    private val callMapper: CallMapper? = null

    @Autowired
    private val callMapperExt: CallMapperExt? = null

    @Autowired
    private val asyncEventBus: AsyncEventBus? = null

    override val crudMapper: ICrudGenericDAO<Int, CallWithBLOBs>
        get() = callMapper as ICrudGenericDAO<Int, CallWithBLOBs>

    override fun findById(callId: Int?, sAccountId: Int?): SimpleCall {
        return callMapperExt!!.findById(callId)
    }

    override val searchMapper: ISearchableDAO<CallSearchCriteria>?
        get() = callMapperExt

    override fun saveWithSession(record: CallWithBLOBs, username: String?): Int {
        val result = super.saveWithSession(record, username)
        asyncEventBus!!.post(CleanCacheEvent(record.saccountid, arrayOf<Class<*>>(EventService::class.java)))
        return result
    }

    override fun updateWithSession(record: CallWithBLOBs, username: String?): Int {
        val result = super.updateWithSession(record, username)
        asyncEventBus!!.post(CleanCacheEvent(record.saccountid, arrayOf<Class<*>>(EventService::class.java)))
        return result
    }

    override fun removeByCriteria(criteria: CallSearchCriteria, accountId: Int) {
        super.removeByCriteria(criteria, accountId)
        asyncEventBus!!.post(CleanCacheEvent(accountId, arrayOf<Class<*>>(EventService::class.java)))
    }

    override fun massRemoveWithSession(items: List<CallWithBLOBs>, username: String?, accountId: Int) {
        super.massRemoveWithSession(items, username, accountId)
        asyncEventBus!!.post(CleanCacheEvent(accountId, arrayOf<Class<*>>(EventService::class.java)))
    }

    override fun massUpdateWithSession(record: CallWithBLOBs, primaryKeys: List<Int>, accountId: Int?) {
        super.massUpdateWithSession(record, primaryKeys, accountId)
        asyncEventBus!!.post(CleanCacheEvent(accountId, arrayOf<Class<*>>(EventService::class.java)))
    }

    override fun updateBySearchCriteria(record: CallWithBLOBs, searchCriteria: CallSearchCriteria) {
        super.updateBySearchCriteria(record, searchCriteria)
        asyncEventBus!!.post(CleanCacheEvent(searchCriteria.saccountid!!.value,
                arrayOf<Class<*>>(EventService::class.java)))
    }

    companion object {
        init {
            ClassInfoMap.put(CallServiceImpl::class.java, ClassInfo(ModuleNameConstants.CRM, CrmTypeConstants.CALL))
        }
    }
}
