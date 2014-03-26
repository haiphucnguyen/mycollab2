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
package com.esofthead.mycollab.module.project.ui.components;

import java.util.GregorianCalendar;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.common.ui.components.ReloadableComponent;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectCommentInput extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private final RichTextArea commentArea;

	private CommentType type;
	private Integer typeid;
	private Integer extraTypeId;

	ProjectCommentInput(
			final ReloadableComponent component,
			final CommentType typeVal,
			final Integer extraTypeIdVal,
			final boolean cancelButtonEnable,
			final boolean isSendingEmailRelay,
			final Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
		/*this.setWidth("600px");
		this.setSpacing(true);
		this.setMargin(true);*/

		/*HorizontalLayout commentWrap = new HorizontalLayout();*/
		super();
		this.setSpacing(true);
		this.setStyleName("message");
		this.setWidth("100%");

		SimpleUser currentUser = AppContext.getSession();
		VerticalLayout userBlock = new VerticalLayout();
		userBlock.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		userBlock.setWidth("80px");
		userBlock.setSpacing(true);
		userBlock.addComponent(UserAvatarControlFactory
				.createUserAvatarButtonLink(
						currentUser.getAvatarid(),
						currentUser.getDisplayName()));
		Label userName = new Label(currentUser.getDisplayName());
		userName.setStyleName("user-name");
		userBlock.addComponent(userName);

		this.addComponent(userBlock);
		VerticalLayout textAreaWrap = new VerticalLayout();
		textAreaWrap.setStyleName("message-container");
		textAreaWrap.setWidth("100%");
		textAreaWrap.setMargin(true);
		textAreaWrap.setSpacing(true);
		this.addComponent(textAreaWrap);
		this.setExpandRatio(textAreaWrap, 1.0f);

		type = typeVal;
		extraTypeId = extraTypeIdVal;

		commentArea = new RichTextArea();
		commentArea.setWidth("100%");
		commentArea.setHeight("200px");

		final AttachmentPanel attachments = new AttachmentPanel();

		final HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setWidth("100%");
		controlsLayout.setSpacing(true);

		final MultiFileUploadExt uploadExt = new MultiFileUploadExt(attachments);
		controlsLayout.addComponent(uploadExt);
		controlsLayout.setComponentAlignment(uploadExt, Alignment.MIDDLE_LEFT);

		final Label emptySpace = new Label();
		controlsLayout.addComponent(emptySpace);
		controlsLayout.setExpandRatio(emptySpace, 1.0f);

		if (cancelButtonEnable) {
			final Button cancelBtn = new Button("Cancel",
					new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					component.cancel();
				}
			});
			cancelBtn.setStyleName(UIConstants.THEME_BLANK_LINK);
			controlsLayout.addComponent(cancelBtn);
			controlsLayout.setComponentAlignment(cancelBtn,
					Alignment.MIDDLE_RIGHT);
		}

		final Button newCommentBtn = new Button("Post",
				new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final Button.ClickEvent event) {
				final Comment comment = new Comment();
				comment.setComment(commentArea.getValue());
				comment.setCreatedtime(new GregorianCalendar()
				.getTime());
				comment.setCreateduser(AppContext.getUsername());
				comment.setSaccountid(AppContext.getAccountId());
				comment.setType(type.toString());
				comment.setTypeid(typeid);
				comment.setExtratypeid(extraTypeId);

				final CommentService commentService = ApplicationContextUtil
						.getSpringBean(CommentService.class);
				int commentId = commentService.saveWithSession(comment,
						AppContext.getUsername(), isSendingEmailRelay,
						emailHandler);

				String attachmentPath = AttachmentUtils
						.getProjectEntityCommentAttachmentPath(typeVal,
								AppContext.getAccountId(),
								CurrentProjectVariables.getProjectId(),
								typeid, commentId);

				if (!"".equals(attachmentPath)) {
					attachments.saveContentsToRepo(attachmentPath);
				}

				// save success, clear comment area and load list
				// comments again
				commentArea.setValue("");
				attachments.removeAllAttachmentsDisplay();
				component.reload();
			}
		});
		newCommentBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		newCommentBtn.setIcon(MyCollabResource.newResource("icons/16/post.png"));
		controlsLayout.addComponent(newCommentBtn);

		textAreaWrap.addComponent(commentArea);
		textAreaWrap.addComponent(attachments);
		textAreaWrap.addComponent(controlsLayout);
	}

	void setTypeAndId(final int typeid) {
		this.typeid = typeid;
	}
}
