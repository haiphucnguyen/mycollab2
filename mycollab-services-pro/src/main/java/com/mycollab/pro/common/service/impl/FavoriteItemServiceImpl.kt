package com.mycollab.pro.common.service.impl

import com.google.common.eventbus.AsyncEventBus
import com.mycollab.cache.CleanCacheEvent
import com.mycollab.common.domain.FavoriteItem
import com.mycollab.common.domain.FavoriteItemExample
import com.mycollab.common.service.FavoriteItemService
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.service.DefaultCrudService
import com.mycollab.module.project.service.ProjectGenericItemService
import com.mycollab.pro.common.dao.FavoriteItemMapper
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
@Service
open class FavoriteItemServiceImpl(private val favoriteItemMapper: FavoriteItemMapper,
                              private val asyncEventBus: AsyncEventBus) : DefaultCrudService<Int, FavoriteItem>(), FavoriteItemService {


    override val crudMapper: ICrudGenericDAO<Int, FavoriteItem>
        get() = favoriteItemMapper as ICrudGenericDAO<Int, FavoriteItem>

    override fun saveOrDelete(item: FavoriteItem) {
        val ex = FavoriteItemExample()
        ex.createCriteria().andTypeEqualTo(item.type).andTypeidEqualTo(item.typeid).andCreateduserEqualTo(item.createduser)
        val count = favoriteItemMapper.countByExample(ex)
        if (count > 0) {
            favoriteItemMapper.deleteByExample(ex)
        } else {
            val now = GregorianCalendar().time
            item.lastupdatedtime = now
            item.createdtime = now
            favoriteItemMapper.insert(item)
        }
        asyncEventBus.post(CleanCacheEvent(item.saccountid, arrayOf(FavoriteItemService::class.java,
                ProjectGenericItemService::class.java)))
    }

    override fun isUserFavorite(username: String, type: String, typeId: String): Boolean {
        val ex = FavoriteItemExample()
        ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeId).andCreateduserEqualTo(username)
        return favoriteItemMapper.countByExample(ex) > 0
    }
}
