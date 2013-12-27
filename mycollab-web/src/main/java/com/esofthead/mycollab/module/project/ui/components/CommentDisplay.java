package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.common.ui.components.CommentRowDisplayHandler;
import com.esofthead.mycollab.common.ui.components.ReloadableComponent;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class CommentDisplay extends VerticalLayout implements
		ReloadableComponent {
	private static final long serialVersionUID = 1L;

	private final BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
	private CommentType type;
	private Integer typeid;
	private Integer numComments;
	private ProjectCommentInput commentBox;

	public CommentDisplay(
			final CommentType type,
			final Integer extraTypeId,
			final boolean isDisplayCommentInput,
			final boolean isSendingRelayEmail,
			final Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
		setSpacing(true);
		this.type = type;

		if (isDisplayCommentInput) {
			commentBox = new ProjectCommentInput(this, type, extraTypeId,
					false, isSendingRelayEmail, emailHandler);
			this.addComponent(commentBox);
		}

		commentList = new BeanList<CommentService, CommentSearchCriteria, SimpleComment>(
				ApplicationContextUtil.getSpringBean(CommentService.class),
				CommentRowDisplayHandler.class);
		commentList.setDisplayEmptyListText(false);
		this.addComponent(commentList);

		displayCommentList();
	}

	@Override
	public void cancel() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private void displayCommentList() {
		if (type == null || typeid == null || typeid == 0) {
			return;
		}

		final CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
		searchCriteria.setType(new StringSearchField(type.toString()));
		searchCriteria.setTypeid(new NumberSearchField(typeid));
		numComments = commentList.setSearchCriteria(searchCriteria);
	}

	public int getNumComments() {
		return numComments;
	}

	public void loadComments(final int typeid) {
		this.typeid = typeid;
		if (commentBox != null) {
			commentBox.setTypeAndId(typeid);
		}
		displayCommentList();
	}

	@Override
	public void reload() {
		displayCommentList();
	}
}