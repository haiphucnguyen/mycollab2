package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;

public interface CommentService extends IDefaultService<Integer, Comment, CommentSearchCriteria> {
	int saveWithSession(Comment record, String username, boolean isSendingEmail);
	
	int saveWithSession(Comment record, String username, boolean isSendingEmail, Class emailHandler);
}
