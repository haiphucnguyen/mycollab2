package com.esofthead.mycollab.pro.common.service.ibatis;

import com.esofthead.mycollab.common.domain.AggregateTag;
import com.esofthead.mycollab.common.domain.Tag;
import com.esofthead.mycollab.common.domain.TagExample;
import com.esofthead.mycollab.common.domain.criteria.TagSearchCriteria;
import com.esofthead.mycollab.common.service.TagService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.pro.common.dao.TagMapper;
import com.esofthead.mycollab.pro.common.dao.TagMapperExt;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
@Service
public class TagServiceImpl extends DefaultCrudService<Integer, Tag> implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagMapperExt tagMapperExt;

    @Override
    public ICrudGenericDAO<Integer, Tag> getCrudMapper() {
        return tagMapper;
    }

    @Override
    public Integer saveWithSession(Tag record, String username) {
        TagExample ex = new TagExample();
        ex.createCriteria().andTypeEqualTo(record.getType()).andTypeidEqualTo(record.getTypeid()).andNameEqualTo
                (record.getName());
        int count = tagMapper.countByExample(ex);
        if (count > 0) {
            return 0;
        }
        tagMapper.insertAndReturnKey(record);
        return record.getId();
    }

    @Override
    public List<Tag> findTags(String type, String typeId, Integer accountId) {
        TagExample ex = new TagExample();
        ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeId);
        return tagMapper.selectByExample(ex);
    }

    @Override
    public List<Tag> findTagsInAccount(String name, String[] types, @CacheKey Integer accountId) {
        TagExample ex = new TagExample();
        ex.createCriteria().andNameLike(name).andTypeIn(Arrays.asList(types)).andSaccountidEqualTo(accountId);
        return tagMapper.selectByExample(ex);
    }

    @Override
    public List<AggregateTag> findTagsInProject(Integer projectId, @CacheKey Integer accountId) {
        TagSearchCriteria searchCriteria = new TagSearchCriteria();
        searchCriteria.setSaccountid(NumberSearchField.and(accountId));
        searchCriteria.setProjectId(NumberSearchField.and(projectId));
        return tagMapperExt.findPagableListByCriteria(searchCriteria, new RowBounds(0, Integer.MAX_VALUE));
    }

    @Override
    public int deleteByExample(TagExample example) {
        return tagMapper.deleteByExample(example);
    }
}