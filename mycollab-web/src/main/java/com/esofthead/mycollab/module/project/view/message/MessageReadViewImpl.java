/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.message;

import java.util.List;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.ui.components.ProjectAttachmentDisplayComponentFactory;
import com.esofthead.mycollab.schedule.email.command.MessageRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class MessageReadViewImpl extends AbstractView implements
		MessageReadView {
	private static final long serialVersionUID = 1L;

	private final PreviewForm previewForm;
	private SimpleMessage message;

	public MessageReadViewImpl() {
		super();
		this.setMargin(false, true, true, true);
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public HasPreviewFormHandlers<SimpleMessage> getPreviewFormHandlers() {
		return previewForm;
	}

	@Override
	public void previewItem(SimpleMessage item) {
		this.message = item;
		previewForm.setItemDataSource(new BeanItem<SimpleMessage>(item));
	}

	@Override
	public SimpleMessage getItem() {
		return message;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<SimpleMessage> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {

					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory implements IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			@Override
			public Layout getLayout() {
				VerticalLayout messageAddLayout = new VerticalLayout();

				HorizontalLayout messageLayout = new HorizontalLayout();
				messageLayout.setStyleName("message");
				messageLayout.setWidth("100%");
				messageLayout.addComponent(UserAvatarControlFactory
						.createUserAvatarButtonLink(
								message.getPostedUserAvatarId(),
								message.getFullPostedUserName()));

				CssLayout rowLayout = new CssLayout();
				rowLayout.setStyleName("message-container");
				rowLayout.setWidth("100%");

				Label title = new Label("<h2 style='color: #006699;'>"
						+ message.getTitle() + "</h2>", Label.CONTENT_XHTML);

				HorizontalLayout messageHeader = new HorizontalLayout();
				messageHeader.setStyleName("message-header");
				VerticalLayout leftHeader = new VerticalLayout();
				leftHeader.setSpacing(true);

				Label username = new Label(message.getFullPostedUserName());
				username.setStyleName("user-name");
				leftHeader.addComponent(username);

				title.addStyleName("message-title");
				leftHeader.addComponent(title);

				VerticalLayout rightHeader = new VerticalLayout();
				Label timePostLbl = new Label(
						DateTimeUtils.getStringDateFromNow(message
								.getPosteddate()));
				timePostLbl.setSizeUndefined();
				timePostLbl.setStyleName("time-post");
				rightHeader.addComponent(timePostLbl);
				rightHeader.setSizeUndefined();

				messageHeader.addComponent(leftHeader);
				messageHeader.setExpandRatio(leftHeader, 1.0f);
				messageHeader.addComponent(rightHeader);
				messageHeader.setWidth("100%");

				rowLayout.addComponent(messageHeader);

				Label messageContent = new Label(message.getMessage(),
						Label.CONTENT_XHTML);
				messageContent.setStyleName("message-body");
				rowLayout.addComponent(messageContent);

				HorizontalLayout attachmentField = new HorizontalLayout();
				Embedded attachmentIcon = new Embedded();
				attachmentIcon.setSource(MyCollabResource
						.newResource("icons/16/attachment.png"));
				attachmentField.addComponent(attachmentIcon);

				Label lbAttachment = new Label("Attachment: ");
				attachmentField.addComponent(lbAttachment);

				rowLayout.addComponent(attachmentField);

				Component attachmentDisplayComp = ProjectAttachmentDisplayComponentFactory
						.getAttachmentDisplayComponent(message.getProjectid(),
								AttachmentUtils.PROJECT_MESSAGE,
								message.getId());
				rowLayout.addComponent(attachmentDisplayComp);

				ResourceService attachmentService = AppContext
						.getSpringBean(ResourceService.class);
				List<Content> attachments = attachmentService
						.getContents(AttachmentUtils
								.getProjectEntityAttachmentPath(
										AppContext.getAccountId(),
										message.getProjectid(),
										AttachmentUtils.PROJECT_MESSAGE,
										message.getId()));
				if (attachments == null || attachments.isEmpty()) {
					attachmentField.setVisible(false);
				}

				messageLayout.addComponent(rowLayout);
				messageLayout.setExpandRatio(rowLayout, 1.0f);

				messageAddLayout.addComponent(messageLayout);
				messageAddLayout.addComponent(createBottomPanel());

				return messageAddLayout;
			}

			protected Layout createBottomPanel() {
				VerticalLayout bottomPanel = new VerticalLayout();
				bottomPanel.addComponent(new CommentListDepot(
						CommentTypeConstants.PRJ_MESSAGE, message.getId(),
						CurrentProjectVariables.getProjectId(), true, true,
						MessageRelayEmailNotificationAction.class));
				return bottomPanel;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
			}
		}
	}
}
