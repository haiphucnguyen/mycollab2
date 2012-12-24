package com.esofthead.mycollab.common.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;

@Service
public class CommentServiceImpl extends DefaultCrudService<Integer, Comment>
		implements CommentService {

	@Autowired
	protected CommentMapper commentMapper;

	@Override
	public ICrudGenericDAO<Integer, Comment> getCrudMapper() {
		return commentMapper;
	}

}
