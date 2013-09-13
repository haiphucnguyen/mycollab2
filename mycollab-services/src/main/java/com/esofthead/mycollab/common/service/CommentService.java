package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;

public interface CommentService extends
		IDefaultService<Integer, Comment, CommentSearchCriteria> {
	@CacheEvict
	int saveWithSession(@CacheKey Comment record, String username,
			boolean isSendingEmail);

	@CacheEvict
	int saveWithSession(@CacheKey Comment record, String username,
			boolean isSendingEmail,
			Class<? extends SendingRelayEmailNotificationAction> emailHandler);
}
