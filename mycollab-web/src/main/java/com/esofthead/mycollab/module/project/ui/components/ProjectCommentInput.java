package com.esofthead.mycollab.module.project.ui.components;

import java.util.GregorianCalendar;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.common.ui.components.ReloadableComponent;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
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
public class ProjectCommentInput extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private final RichTextArea commentArea;
	private String type;
	private Integer typeid;
	private Integer extraTypeId;

	public ProjectCommentInput(final ReloadableComponent component,
			final String typeVal, final Integer typeidVal,
			final Integer extraTypeIdVal, final boolean cancelButtonEnable,
			final boolean isSendingEmailRelay) {
		this(component, typeVal, typeidVal, extraTypeIdVal, cancelButtonEnable,
				isSendingEmailRelay, null);
	}

	ProjectCommentInput(
			final ReloadableComponent component,
			final String typeVal,
			final Integer typeidVal,
			final Integer extraTypeIdVal,
			final boolean cancelButtonEnable,
			final boolean isSendingEmailRelay,
			final Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
		this.setWidth("600px");
		setSpacing(true);
		this.setMargin(true);

		type = typeVal;
		typeid = typeidVal;
		extraTypeId = extraTypeIdVal;

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
						comment.setExtratypeid(extraTypeId);

						final CommentService commentService = AppContext
								.getSpringBean(CommentService.class);
						int commentId = commentService.saveWithSession(comment,
								AppContext.getUsername(), isSendingEmailRelay,
								emailHandler);

						String attachmentPath = "";
						if (CommentTypeConstants.PRJ_BUG.equals(type)) {
							attachmentPath = AttachmentUtils
									.getProjectBugCommentAttachmentPath(
											AppContext.getAccountId(),
											CurrentProjectVariables
													.getProjectId(), typeid,
											commentId);
						} else if (CommentTypeConstants.PRJ_MESSAGE
								.equals(type)) {
							attachmentPath = AttachmentUtils
									.getProjectMessageCommentAttachmentPath(
											AppContext.getAccountId(),
											CurrentProjectVariables
													.getProjectId(), typeid,
											commentId);
						} else if (CommentTypeConstants.PRJ_MILESTONE
								.equals(type)) {
							attachmentPath = AttachmentUtils
									.getProjectMilestoneCommentAttachmentPath(
											AppContext.getAccountId(),
											CurrentProjectVariables
													.getProjectId(), typeid,
											commentId);
						} else if (CommentTypeConstants.PRJ_PROBLEM
								.equals(type)) {
							attachmentPath = AttachmentUtils
									.getProjectProblemCommentAttachmentPath(
											AppContext.getAccountId(),
											CurrentProjectVariables
													.getProjectId(), typeid,
											commentId);
						} else if (CommentTypeConstants.PRJ_RISK.equals(type)) {
							attachmentPath = AttachmentUtils
									.getProjectRiskCommentAttachmentPath(
											AppContext.getAccountId(),
											CurrentProjectVariables
													.getProjectId(), typeid,
											commentId);
						} else if (CommentTypeConstants.PRJ_TASK.equals(type)) {
							attachmentPath = AttachmentUtils
									.getProjectTaskCommentAttachmentPath(
											AppContext.getAccountId(),
											CurrentProjectVariables
													.getProjectId(), typeid,
											commentId);
						} else if (CommentTypeConstants.PRJ_TASK_LIST
								.equals(type)) {
							attachmentPath = AttachmentUtils
									.getProjectTaskListCommentAttachmentPath(
											AppContext.getAccountId(),
											CurrentProjectVariables
													.getProjectId(), typeid,
											commentId);
						} else {
							// do nothing
						}

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
		newCommentBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlsLayout.addComponent(newCommentBtn);

		this.addComponent(commentArea);
		this.addComponent(attachments);
		this.addComponent(controlsLayout);
	}

	void setTypeAndId(final String type, final int typeid) {
		this.type = type;
		this.typeid = typeid;
	}
}
