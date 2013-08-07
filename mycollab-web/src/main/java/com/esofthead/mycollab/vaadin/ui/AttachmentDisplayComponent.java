/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.file.StreamDownloadResourceFactory;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class AttachmentDisplayComponent extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public AttachmentDisplayComponent(List<Content> attachments) {
		for (Content attachment : attachments) {
			this.addComponent(constructAttachmentRow(attachment));
		}
	}

	public static Component constructAttachmentRow(final Content attachment) {
		String docName = attachment.getPath();
		int lastIndex = docName.lastIndexOf("/");
		if (lastIndex != -1) {
			docName = docName.substring(lastIndex + 1, docName.length());
		}

		final HorizontalLayout attachmentLayout = new HorizontalLayout();
		attachmentLayout.setSpacing(true);
		attachmentLayout.setMargin(false, false, false, true);

		Embedded fileTypeIcon = new Embedded(null,
				UiUtils.getFileIconResource(docName));
		attachmentLayout.addComponent(fileTypeIcon);

		String fileExt = "";
		int index = docName.lastIndexOf(".");
		if (index > 0) {
			fileExt = docName.substring(index + 1, docName.length());
			docName = docName.substring(0, index);
		}
		docName = StringUtils.trimString(docName, 60, true);

		Label attachmentLink = new Label(docName);
		attachmentLayout.addComponent(attachmentLink);
		attachmentLayout.setComponentAlignment(attachmentLink,
				Alignment.MIDDLE_CENTER);

		if ("jpg".equalsIgnoreCase(fileExt) || "png".equalsIgnoreCase(fileExt)) {

			Button previewBtn = new Button(null, new Button.ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					Resource previewResource = StreamDownloadResourceFactory
							.getImagePreviewResource(attachment.getPath());
					AppContext
							.getApplication()
							.getMainWindow()
							.addWindow(
									new AttachmentPreviewWindow(previewResource));
				}
			});
			previewBtn.setIcon(MyCollabResource
					.newResource("icons/16/preview.png"));
			previewBtn.setStyleName("link");
			attachmentLayout.addComponent(previewBtn);
		}

		Button trashBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				ConfirmDialogExt.show(
						AppContext.getApplication().getMainWindow(),
						LocalizationHelper
								.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										ApplicationProperties
												.getString(ApplicationProperties.SITE_NAME)),
						LocalizationHelper
								.getMessage(GenericI18Enum.CONFIRM_DELETE_ATTACHMENT),
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
						new ConfirmDialog.Listener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									ResourceService attachmentService = AppContext
											.getSpringBean(ResourceService.class);
									attachmentService.removeResource(
											attachment.getPath(),
											AppContext.getUsername());
									((ComponentContainer) attachmentLayout
											.getParent())
											.removeComponent(attachmentLayout);
								}
							}
						});

			}
		});
		trashBtn.setIcon(MyCollabResource.newResource("icons/16/trash.png"));
		trashBtn.setStyleName("link");
		attachmentLayout.addComponent(trashBtn);

		Button downloadBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Resource downloadResource = StreamDownloadResourceFactory
						.getAccountStreamResource(attachment.getPath());
				AppContext.getApplication().getMainWindow()
						.open(downloadResource, "_blank");
			}
		});
		downloadBtn.setIcon(MyCollabResource
				.newResource("icons/16/download.png"));
		downloadBtn.setStyleName("link");
		attachmentLayout.addComponent(downloadBtn);
		return attachmentLayout;
	}

	public static Component getAttachmentDisplayComponent(String type,
			int typeid) {
		ResourceService attachmentService = AppContext
				.getSpringBean(ResourceService.class);
		List<Content> attachments = attachmentService
				.getContents(AttachmentUtils.getAttachmentPath(
						AppContext.getAccountId(), type, typeid));
		if (attachments != null && !attachments.isEmpty()) {
			return new AttachmentDisplayComponent(attachments);
		} else {
			return new VerticalLayout();
		}
	}
}
