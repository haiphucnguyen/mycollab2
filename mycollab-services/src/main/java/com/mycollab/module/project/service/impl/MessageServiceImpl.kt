package com.mycollab.module.project.service.impl

import com.google.common.eventbus.AsyncEventBus
import com.mycollab.aspect.ClassInfo
import com.mycollab.aspect.ClassInfoMap
import com.mycollab.aspect.Traceable
import com.mycollab.cache.CleanCacheEvent
import com.mycollab.common.ModuleNameConstants
import com.mycollab.core.cache.CleanCache
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.db.persistence.service.DefaultService
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.dao.MessageMapper
import com.mycollab.module.project.dao.MessageMapperExt
import com.mycollab.module.project.domain.Message
import com.mycollab.module.project.domain.SimpleMessage
import com.mycollab.module.project.domain.criteria.MessageSearchCriteria
import com.mycollab.module.project.esb.DeleteProjectMessageEvent
import com.mycollab.module.project.service.MessageService
import com.mycollab.module.project.service.ProjectActivityStreamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
@Transactional
@Traceable(nameField = "title", extraFieldName = "projectid")
class MessageServiceImpl : DefaultService<Int, Message, MessageSearchCriteria>(), MessageService {

    @Autowired
    private val messageMapper: MessageMapper? = null

    @Autowired
    private val messageMapperExt: MessageMapperExt? = null

    @Autowired
    private val asyncEventBus: AsyncEventBus? = null

    override val crudMapper: ICrudGenericDAO<Int, Message>
        get() = messageMapper as ICrudGenericDAO<Int, Message>

    @CleanCache
    fun postDirtyUpdate(sAccountId: Int?) {
        asyncEventBus!!.post(CleanCacheEvent(sAccountId, arrayOf<Class<*>>(ProjectActivityStreamService::class.java)))
    }

    override fun massRemoveWithSession(items: List<Message>, username: String?, sAccountId: Int) {
        super.massRemoveWithSession(items, username, sAccountId)
        val event = DeleteProjectMessageEvent(items.toTypedArray(), username, sAccountId)
        asyncEventBus!!.post(event)
    }

    override val searchMapper: ISearchableDAO<MessageSearchCriteria>?
        get() = messageMapperExt

    override fun findById(messageId: Int?, sAccountId: Int?): SimpleMessage {
        return messageMapperExt!!.findMessageById(messageId!!)
    }

    companion object {

        init {
            ClassInfoMap.put(MessageServiceImpl::class.java, ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.MESSAGE))
        }
    }
}
