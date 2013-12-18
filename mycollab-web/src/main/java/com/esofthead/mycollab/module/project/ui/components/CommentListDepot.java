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
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.ui.Depot;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CommentListDepot extends Depot {
	private static final long serialVersionUID = 1L;

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

	public CommentListDepot(
			final CommentType type,
			final int extraTypeId,
			final boolean isDisplayCommentInput,
			final boolean isSendingRelayEmail,
			final Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
		super("Comments", new CommentDisplay(type, extraTypeId,
				isDisplayCommentInput, isSendingRelayEmail, emailHandler));
		this.setWidth("900px");
		addStyleName("comment-list");
		commentListBox = (CommentDisplay) bodyContent;
		commentListBox.setMargin(true);
	}

	public void loadComments(final CommentType type, final int typeid) {
		commentListBox.loadComments(type, typeid);
		setTitle("Comments(" + commentListBox.getNumComments() + ")");
	}
}
