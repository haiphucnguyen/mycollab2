/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.MyCollabException;
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

	private CommentDisplay commentListBox;

	public CommentListDepot(boolean isDisplayCommentInput) {
		super("Comments", new CommentDisplay(isDisplayCommentInput));
		this.setWidth("900px");
		commentListBox = (CommentDisplay) this.bodyContent;
		commentListBox.setMargin(true);
	}
	
	public CommentListDepot(boolean isDisplayCommentInput, Class emailHandler) {
		super("Comments", new CommentDisplay(isDisplayCommentInput, emailHandler));
		this.setWidth("900px");
		commentListBox = (CommentDisplay) this.bodyContent;
		commentListBox.setMargin(true);
	}

//	public CommentListDepot(String type, int typeid) {
//		this(type, typeid, true);
//	}

	public CommentListDepot(String type, int typeid,
			boolean isDisplayCommentInput, boolean isSendingRelayEmail) {
		super("Comments", new CommentDisplay(type, typeid,
				isDisplayCommentInput, isSendingRelayEmail, null));
		this.setWidth("900px");
		commentListBox = (CommentDisplay) this.bodyContent;
		this.setTitle("Comments(" + commentListBox.getNumComments() + ")");
		commentListBox.setMargin(true);
	}
	
	public CommentListDepot(String type, int typeid,
			boolean isDisplayCommentInput, boolean isSendingRelayEmail, Class emailHandler) {
		super("Comments", new CommentDisplay(type, typeid,
				isDisplayCommentInput, isSendingRelayEmail, emailHandler));
		this.setWidth("900px");
		commentListBox = (CommentDisplay) this.bodyContent;
		this.setTitle("Comments(" + commentListBox.getNumComments() + ")");
		commentListBox.setMargin(true);
	}

	public void loadComments(String type, int typeid) {
		commentListBox.loadComments(type, typeid);
		this.setTitle("Comments(" + commentListBox.getNumComments() + ")");
	}

	public static class CommentDisplay extends VerticalLayout implements
			ReloadableComponent {
		private BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
		private String type;
		private int typeid;
		private int numComments;
		private CommentInput commentBox;

		public CommentDisplay(boolean isDisplayCommentInput) {
			this(isDisplayCommentInput, null);
		}
		
		public CommentDisplay(boolean isDisplayCommentInput, Class emailHandler) {
			this.setSpacing(true);
			if (isDisplayCommentInput) {
				commentBox = new CommentInput(this, type, typeid, emailHandler);
				this.addComponent(commentBox);
			}

			commentList = new BeanList<CommentService, CommentSearchCriteria, SimpleComment>(
					AppContext.getSpringBean(CommentService.class),
					CommentRowDisplayHandler.class);
			this.addComponent(commentList);
		}

		// public CommentDisplay(String type, int typeid) {
		// this(type, typeid, true);
		// }
		
		public CommentDisplay(String type, int typeid,
				boolean isDisplayCommentInput, boolean isSendingRelayEmail) {
			this (type, typeid, isDisplayCommentInput, isSendingRelayEmail, null);
		}

		public CommentDisplay(String type, int typeid,
				boolean isDisplayCommentInput, boolean isSendingRelayEmail, Class emailHandler) {
			this.setSpacing(true);
			this.type = type;
			this.typeid = typeid;

			if (isDisplayCommentInput) {
				commentBox = new CommentInput(this, type, typeid, false,
						isSendingRelayEmail, emailHandler);
				this.addComponent(commentBox);
			}

			commentList = new BeanList<CommentService, CommentSearchCriteria, SimpleComment>(
					AppContext.getSpringBean(CommentService.class),
					CommentRowDisplayHandler.class);
			this.addComponent(commentList);

			displayCommentList();
		}

		public void loadComments(String type, int typeid) {
			this.type = type;
			this.typeid = typeid;
			displayCommentList();

			if (commentBox != null) {
				commentBox.setTypeAndId(type, typeid);
			}
		}

		private void displayCommentList() {
			if (type == null || typeid == 0) {
				throw new MyCollabException("Parameters are invalid");
			}

			CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
			searchCriteria.setType(new StringSearchField(type));
			searchCriteria.setTypeid(new NumberSearchField(typeid));
			numComments = commentList.setSearchCriteria(searchCriteria);
		}

		@Override
		public void cancel() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void reload() {
			displayCommentList();
		}

		public int getNumComments() {
			return numComments;
		}
	}
}
