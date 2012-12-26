package com.esofthead.mycollab.common.service.ibatis;

import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.dao.CommentMapperExt;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends DefaultService<Integer, Comment, CommentSearchCriteria>
        implements CommentService {

    @Autowired
    protected CommentMapper commentMapper;
    
    @Autowired
    protected CommentMapperExt commentMapperExt;

    @Override
    public ICrudGenericDAO<Integer, Comment> getCrudMapper() {
        return commentMapper;
    }

    @Override
    public ISearchableDAO<CommentSearchCriteria> getSearchMapper() {
        return commentMapperExt;
    }
}
