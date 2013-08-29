package com.esofthead.mycollab.common.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.dao.CommentMapper;
import com.esofthead.mycollab.common.dao.CommentMapperExt;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;

@Service
public class CommentServiceImpl extends
		DefaultService<Integer, Comment, CommentSearchCriteria> implements
		CommentService {

	@Autowired
	protected CommentMapper commentMapper;

	@Autowired
	protected CommentMapperExt commentMapperExt;

	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

	@Override
	public ICrudGenericDAO<Integer, Comment> getCrudMapper() {
		return commentMapper;
	}

	@Override
	public ISearchableDAO<CommentSearchCriteria> getSearchMapper() {
		return commentMapperExt;
	}

	@Override
	public int saveWithSession(Comment record, String username) {
		return this.saveWithSession(record, username, false);
	}

	@Override
	public int saveWithSession(Comment record, String username,
			boolean isSendingEmail) {
		int saveId = super.saveWithSession(record, username);
		if (!isSendingEmail) {
			return saveId;
		} else {
			relayEmailNotificationService.saveWithSession(
					getRelayEmailNotification(record, username, isSendingEmail,
							null), username);
			return saveId;
		}
	}

	private RelayEmailNotification getRelayEmailNotification(Comment record,
			String username, boolean isSendingEmail,
			Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
		RelayEmailNotification relayEmailNotification = new RelayEmailNotification();
		relayEmailNotification.setSaccountid(record.getSaccountid());
		relayEmailNotification
				.setAction(MonitorTypeConstants.ADD_COMMENT_ACTION);
		relayEmailNotification.setChangeby(record.getCreateduser());
		relayEmailNotification.setChangecomment(record.getComment());
		relayEmailNotification.setType(record.getType());
		relayEmailNotification.setTypeid(record.getTypeid());
		if (emailHandler != null) {
			relayEmailNotification.setEmailhandlerbean(emailHandler.getName());
		}
		return relayEmailNotification;
	}

	@Override
	public int saveWithSession(Comment record, String username,
			boolean isSendingEmail,
			Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
		int saveId = super.saveWithSession(record, username);
		if (!isSendingEmail) {
			return saveId;
		} else {
			relayEmailNotificationService.saveWithSession(
					getRelayEmailNotification(record, username, isSendingEmail,
							emailHandler), username);
			return saveId;
		}
	}

}
