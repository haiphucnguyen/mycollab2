package com.mycollab.pro.common.service.impl

import com.mycollab.common.domain.AggregateTag
import com.mycollab.common.domain.Tag
import com.mycollab.common.domain.TagExample
import com.mycollab.common.domain.criteria.TagSearchCriteria
import com.mycollab.common.service.TagService
import com.mycollab.core.cache.CacheKey
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.service.DefaultCrudService
import com.mycollab.pro.common.dao.TagMapper
import com.mycollab.pro.common.dao.TagMapperExt
import org.apache.ibatis.session.RowBounds
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
@Service
open class TagServiceImpl(private val tagMapper: TagMapper,
                     private val tagMapperExt: TagMapperExt) : DefaultCrudService<Int, Tag>(), TagService {

    override val crudMapper: ICrudGenericDAO<Int, Tag>
        get() = tagMapper as ICrudGenericDAO<Int, Tag>

    override fun saveWithSession(record: Tag, username: String?): Int {
        val ex = TagExample()
        ex.createCriteria().andTypeEqualTo(record.type).andTypeidEqualTo(record.typeid).andNameEqualTo(record.name)
        val count = tagMapper.countByExample(ex)
        if (count > 0) {
            return 0
        }
        tagMapper.insertAndReturnKey(record)
        return record.id
    }

    override fun findTags(type: String, typeId: String, accountId: Int?): List<Tag> {
        val ex = TagExample()
        ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeId)
        return tagMapper.selectByExample(ex)
    }

    override fun findTagsInAccount(name: String, types: Array<String>, @CacheKey accountId: Int?): List<Tag> {
        val ex = TagExample()
        ex.createCriteria().andNameLike(name).andTypeIn(Arrays.asList(*types)).andSaccountidEqualTo(accountId)
        return tagMapper.selectByExample(ex)
    }

    override fun findTagsInProject(projectId: Int, @CacheKey accountId: Int): List<AggregateTag> {
        val searchCriteria = TagSearchCriteria()
        searchCriteria.saccountid = NumberSearchField.equal(accountId)
        searchCriteria.projectId = NumberSearchField.equal(projectId)
        return tagMapperExt.findPageableListByCriteria(searchCriteria, RowBounds(0, Integer.MAX_VALUE))
    }

    override fun deleteByExample(example: TagExample): Int? =
            tagMapper.deleteByExample(example)
}
