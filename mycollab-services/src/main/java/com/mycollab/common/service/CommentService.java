package com.mycollab.common.service;

import com.mycollab.common.domain.CommentWithBLOBs;
import com.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.mycollab.db.persistence.service.IDefaultService;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface CommentService extends IDefaultService<Integer, CommentWithBLOBs, CommentSearchCriteria> {
}
