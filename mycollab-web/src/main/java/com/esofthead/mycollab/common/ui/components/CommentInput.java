/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import java.util.GregorianCalendar;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class CommentInput extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private final RichTextArea commentArea;
	private String type;
	private Integer typeid;

	public CommentInput(final ReloadableComponent component, final String type,
			final Integer typeid) {
		this(component, type, typeid, false, false, null);
	}

	public CommentInput(final ReloadableComponent component,
			final String typeVal, final Integer typeidVal,
			final boolean cancelButtonEnable, final boolean isSendingEmailRelay) {
		this(component, typeVal, typeidVal, cancelButtonEnable,
				isSendingEmailRelay, null);
	}

	public CommentInput(final ReloadableComponent component,
			final String typeVal, final Integer typeidVal,
			final boolean cancelButtonEnable,
			final boolean isSendingEmailRelay, final Class emailHandler) {
		this.setWidth("600px");
		setSpacing(true);
		this.setMargin(true);

		type = typeVal;
		typeid = typeidVal;

		commentArea = new RichTextArea();
		commentArea.setWidth("100%");

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
			cancelBtn.setStyleName("link");
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
						comment.setComment((String) commentArea.getValue());
						comment.setCreatedtime(new GregorianCalendar()
								.getTime());
						comment.setCreateduser(AppContext.getUsername());
						comment.setSaccountid(AppContext.getAccountId());
						comment.setType(type);
						comment.setTypeid(typeid);

						final CommentService commentService = AppContext
								.getSpringBean(CommentService.class);
						int commentId = 0;
						if (isSendingEmailRelay) {
							commentId = commentService.saveWithSession(comment,
									AppContext.getUsername(),
									isSendingEmailRelay, emailHandler);
						} else {
							commentId = commentService.saveWithSession(comment,
									AppContext.getUsername(), false,
									emailHandler);
						}

						attachments.saveContentsToRepo(
								AttachmentConstants.COMMON_COMMENT, commentId);

						// save success, clear comment area and load list
						// comments again
						commentArea.setValue("");
						attachments.removeAllAttachmentsDisplay();
						component.reload();
					}
				});
		newCommentBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlsLayout.addComponent(newCommentBtn);

		this.addComponent(commentArea);
		this.addComponent(attachments);
		this.addComponent(controlsLayout);
	}

	public CommentInput(final ReloadableComponent component, final String type,
			final Integer typeid, final Class emailHandler) {
		this(component, type, typeid, false, false, emailHandler);
	}

	public void setTypeAndId(final String type, final int typeid) {
		this.type = type;
		this.typeid = typeid;
	}
}
