package com.mycollab.common.service;

import com.mycollab.common.domain.AggregateTag;
import com.mycollab.common.domain.Tag;
import com.mycollab.common.domain.TagExample;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.ICrudService;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public interface TagService extends ICrudService<Integer, Tag> {
    @Cacheable
    List<Tag> findTags(String type, String typeId, @CacheKey Integer accountId);

    @Cacheable
    List<Tag> findTagsInAccount(String name, String[] types, @CacheKey Integer accountId);

    @Cacheable
    List<AggregateTag> findTagsInProject(Integer projectId, @CacheKey Integer accountId);

    Integer deleteByExample(TagExample example);
}
