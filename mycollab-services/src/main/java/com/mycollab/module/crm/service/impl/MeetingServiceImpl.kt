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
import com.mycollab.module.crm.dao.MeetingMapper
import com.mycollab.module.crm.dao.MeetingMapperExt
import com.mycollab.module.crm.domain.MeetingWithBLOBs
import com.mycollab.module.crm.domain.SimpleMeeting
import com.mycollab.module.crm.domain.criteria.MeetingSearchCriteria
import com.mycollab.module.crm.service.EventService
import com.mycollab.module.crm.service.MeetingService
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
@Watchable(userFieldName = "createduser")
class MeetingServiceImpl : DefaultService<Int, MeetingWithBLOBs, MeetingSearchCriteria>(), MeetingService {

    @Autowired
    private val meetingMapper: MeetingMapper? = null

    @Autowired
    private val meetingMapperExt: MeetingMapperExt? = null

    @Autowired
    private val asyncEventBus: AsyncEventBus? = null

    override val crudMapper: ICrudGenericDAO<Int, MeetingWithBLOBs>
        get() = meetingMapper as ICrudGenericDAO<Int, MeetingWithBLOBs>

    override fun findById(meetingId: Int?, sAccountId: Int?): SimpleMeeting {
        return meetingMapperExt!!.findById(meetingId)
    }

    override val searchMapper: ISearchableDAO<MeetingSearchCriteria>?
        get() = meetingMapperExt

    override fun saveWithSession(record: MeetingWithBLOBs, username: String): Int {
        val result = super.saveWithSession(record, username)
        asyncEventBus!!.post(CleanCacheEvent(record.saccountid, arrayOf<Class<*>>(EventService::class.java)))
        return result
    }

    override fun updateWithSession(record: MeetingWithBLOBs, username: String): Int {
        val result = super.updateWithSession(record, username)
        asyncEventBus!!.post(CleanCacheEvent(record.saccountid, arrayOf<Class<*>>(EventService::class.java)))
        return result
    }

    override fun removeByCriteria(criteria: MeetingSearchCriteria, sAccountId: Int) {
        super.removeByCriteria(criteria, sAccountId)
        asyncEventBus!!.post(CleanCacheEvent(sAccountId, arrayOf<Class<*>>(EventService::class.java)))
    }

    override fun massRemoveWithSession(items: List<MeetingWithBLOBs>, username: String, sAccountId: Int) {
        super.massRemoveWithSession(items, username, sAccountId)
        asyncEventBus!!.post(CleanCacheEvent(sAccountId, arrayOf<Class<*>>(EventService::class.java)))
    }

    override fun massUpdateWithSession(record: MeetingWithBLOBs, primaryKeys: List<Int>, accountId: Int?) {
        super.massUpdateWithSession(record, primaryKeys, accountId)
        asyncEventBus!!.post(CleanCacheEvent(accountId, arrayOf<Class<*>>(EventService::class.java)))
    }

    override fun updateBySearchCriteria(record: MeetingWithBLOBs, searchCriteria: MeetingSearchCriteria) {
        super.updateBySearchCriteria(record, searchCriteria)
        asyncEventBus!!.post(CleanCacheEvent(searchCriteria.saccountid!!.value,
                arrayOf<Class<*>>(EventService::class.java)))
    }

    companion object {
        init {
            ClassInfoMap.put(MeetingServiceImpl::class.java, ClassInfo(ModuleNameConstants.CRM, CrmTypeConstants.MEETING))
        }
    }
}
