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
package com.esofthead.mycollab.module.project.view.message;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.project.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.ui.components.ProjectAttachmentDisplayComponentFactory;
import com.esofthead.mycollab.schedule.email.project.MessageRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 */
@ViewComponent
public class MessageReadViewImpl extends AbstractPageView implements
		MessageReadView {
	private static final long serialVersionUID = 1L;

	private final PreviewForm previewForm;
	private SimpleMessage message;

	public MessageReadViewImpl() {
		super();
		this.setMargin(new MarginInfo(false, true, true, true));
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
						+ message.getTitle() + "</h2>", ContentMode.HTML);

				HorizontalLayout messageHeader = new HorizontalLayout();
				messageHeader.setStyleName("message-header");
				VerticalLayout leftHeader = new VerticalLayout();
				leftHeader.setSpacing(true);

				Label username = new Label(message.getFullPostedUserName());
				username.setStyleName("user-name");
				leftHeader.addComponent(username);

				title.addStyleName("message-title");
				leftHeader.addComponent(title);

				final HorizontalLayout rightHeader = new HorizontalLayout();
				rightHeader.setSpacing(true);
				final VerticalLayout infoLayout = new VerticalLayout();
				Label timePostLbl = new Label(
						DateTimeUtils.getStringDateFromNow(message
								.getPosteddate()));
				timePostLbl.setSizeUndefined();
				timePostLbl.setStyleName("time-post");
				infoLayout.addComponent(timePostLbl);
				infoLayout.setSizeUndefined();

				Button deleteBtn = new Button("", new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						ConfirmDialogExt.show(
								UI.getCurrent(),
								LocalizationHelper.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										SiteConfiguration.getSiteName()),
								LocalizationHelper
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(
											final ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											final MessageService messageService = ApplicationContextUtil
													.getSpringBean(MessageService.class);
											messageService.removeWithSession(
													message.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
											previewForm.fireCancelForm(message);
										}
									}
								});
					}
				});
				deleteBtn.setIcon(MyCollabResource
						.newResource("icons/12/project/icon_x.png"));
				deleteBtn.addStyleName("link");
				deleteBtn.setEnabled(CurrentProjectVariables
						.canAccess(ProjectRolePermissionCollections.MESSAGES));

				rightHeader.addComponent(infoLayout);
				rightHeader.addComponent(deleteBtn);
				rightHeader.setExpandRatio(infoLayout, 1.0f);

				messageHeader.addComponent(leftHeader);
				messageHeader.setExpandRatio(leftHeader, 1.0f);
				messageHeader.addComponent(rightHeader);
				messageHeader.setWidth("100%");

				rowLayout.addComponent(messageHeader);

				Label messageContent = new Label(
						StringUtils.formatExtraLink(message.getMessage()),
						ContentMode.HTML);
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
								AttachmentType.PROJECT_MESSAGE, message.getId());
				rowLayout.addComponent(attachmentDisplayComp);

				ResourceService attachmentService = ApplicationContextUtil
						.getSpringBean(ResourceService.class);
				List<Content> attachments = attachmentService
						.getContents(AttachmentUtils
								.getProjectEntityAttachmentPath(
										AppContext.getAccountId(),
										message.getProjectid(),
										AttachmentType.PROJECT_MESSAGE,
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
						CommentType.PRJ_MESSAGE, message.getId(),
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
