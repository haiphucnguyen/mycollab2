/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 */
@SuppressWarnings("serial")
public class CommentListDepot extends Depot {

	private final CommentDisplay commentListBox;

	public CommentListDepot(final CommentType type, final Integer typeid,
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

	public CommentListDepot(
			final CommentType type,
			final int typeid,
			final int extraTypeId,
			final boolean isDisplayCommentInput,
			final boolean isSendingRelayEmail,
			final Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
		super("Comments", new CommentDisplay(type, typeid, extraTypeId,
				isDisplayCommentInput, isSendingRelayEmail, emailHandler));
		this.setWidth("900px");
		addStyleName("comment-list");
		commentListBox = (CommentDisplay) bodyContent;
		setTitle("Comments(" + commentListBox.getNumComments() + ")");
		commentListBox.setMargin(true);
	}

	public void loadComments(final CommentType type, final int typeid) {
		commentListBox.loadComments(type, typeid);
		setTitle("Comments(" + commentListBox.getNumComments() + ")");
	}

	public static class CommentDisplay extends VerticalLayout implements
			ReloadableComponent {
		private final BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
		private CommentType type;
		private Integer typeid;
		private Integer numComments;
		private ProjectCommentInput commentBox;

		public CommentDisplay(
				final CommentType type,
				final Integer typeid,
				final Integer extraTypeId,
				final boolean isDisplayCommentInput,
				final boolean isSendingRelayEmail,
				final Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
			setSpacing(true);
			this.type = type;
			this.typeid = typeid;

			if (isDisplayCommentInput) {
				commentBox = new ProjectCommentInput(this, type, typeid,
						extraTypeId, false, isSendingRelayEmail, emailHandler);
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

		public void loadComments(final CommentType type, final int typeid) {
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
