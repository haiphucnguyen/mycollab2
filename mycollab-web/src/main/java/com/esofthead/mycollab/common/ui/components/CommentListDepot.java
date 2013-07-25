/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class CommentListDepot extends Depot {

	private final CommentDisplay commentListBox;

	public CommentListDepot(final String type, final Integer typeid,
			final Integer extraTypeId, final boolean isDisplayCommentInput,
			final boolean isSendingRelayEmail) {
		super("Comments", new CommentDisplay(type, typeid, extraTypeId,
				isDisplayCommentInput, isSendingRelayEmail, null));
		this.setWidth("900px");
		addStyleName("comment-list");
		commentListBox = (CommentDisplay) bodyContent;
		setTitle("Comments(" + commentListBox.getNumComments() + ")");
		commentListBox.setMargin(true);
	}

	public CommentListDepot(final String type, final int typeid,
			final int extraTypeId, final boolean isDisplayCommentInput,
			final boolean isSendingRelayEmail, final Class emailHandler) {
		super("Comments", new CommentDisplay(type, typeid, extraTypeId,
				isDisplayCommentInput, isSendingRelayEmail, emailHandler));
		this.setWidth("900px");
		addStyleName("comment-list");
		commentListBox = (CommentDisplay) bodyContent;
		setTitle("Comments(" + commentListBox.getNumComments() + ")");
		commentListBox.setMargin(true);
	}

	public void loadComments(final String type, final int typeid) {
		commentListBox.loadComments(type, typeid);
		setTitle("Comments(" + commentListBox.getNumComments() + ")");
	}

	public static class CommentDisplay extends VerticalLayout implements
			ReloadableComponent {
		private final BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
		private String type;
		private Integer typeid;
		private Integer numComments;
		private CommentInput commentBox;

		public CommentDisplay(final String type, final Integer typeid,
				final Integer extraTypeId, final boolean isDisplayCommentInput,
				final boolean isSendingRelayEmail, final Class emailHandler) {
			setSpacing(true);
			this.type = type;
			this.typeid = typeid;

			if (isDisplayCommentInput) {
				commentBox = new CommentInput(this, type, typeid, extraTypeId,
						false, isSendingRelayEmail, emailHandler);
				this.addComponent(commentBox);
			}

			commentList = new BeanList<CommentService, CommentSearchCriteria, SimpleComment>(
					AppContext.getSpringBean(CommentService.class),
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
			searchCriteria.setType(new StringSearchField(type));
			searchCriteria.setTypeid(new NumberSearchField(typeid));
			numComments = commentList.setSearchCriteria(searchCriteria);
		}

		public int getNumComments() {
			return numComments;
		}

		public void loadComments(final String type, final int typeid) {
			this.type = type;
			this.typeid = typeid;
			displayCommentList();

			if (commentBox != null) {
				commentBox.setTypeAndId(type, typeid);
			}
		}

		@Override
		public void reload() {
			displayCommentList();
		}
	}
}
