/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.module.file.StreamDownloadResource;
import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.file.service.ContentService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class AttachmentDisplayComponent extends VerticalLayout {

    public AttachmentDisplayComponent(List<Attachment> attachments) {
        for (Attachment attachment : attachments) {
            this.addComponent(constructAttachmentRow(attachment));
        }
    }

    public static Component constructAttachmentRow(final Attachment attachment) {
        String docName = attachment.getDocumentpath();
        int lastIndex = docName.lastIndexOf("/");
        if (lastIndex != -1) {
            docName = docName.substring(lastIndex + 1,
                    docName.length());
        }

        final HorizontalLayout attachmentLayout = new HorizontalLayout();
        attachmentLayout.setSpacing(true);
        attachmentLayout.setMargin(false, false, false, true);

        Embedded fileTypeIcon = new Embedded(null, UiUtils.getFileIconResource(docName));
        attachmentLayout.addComponent(fileTypeIcon);
        Label attachmentLink = new Label(docName);
        attachmentLayout.addComponent(attachmentLink);
        attachmentLayout.setComponentAlignment(attachmentLink, Alignment.MIDDLE_CENTER);

        Button trashBtn = new Button(null, new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                AttachmentService attachmentService = AppContext.getSpringBean(AttachmentService.class);
                attachmentService.removeAttachment(AppContext.getAccountId(), attachment);
                ((ComponentContainer)attachmentLayout.getParent()).removeComponent(attachmentLayout);
            }
        });
        trashBtn.setIcon(new ThemeResource("icons/16/trash.png"));
        trashBtn.setStyleName("link");
        attachmentLayout.addComponent(trashBtn);

        Button downloadBtn = new Button(null, new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                ContentService contentService = AppContext.getSpringBean(ContentService.class);
                InputStream inStream = contentService.getContent(AppContext.getAccountId(), attachment.getDocumentpath());
                
                if (inStream != null) {
                    String docPath = attachment.getDocumentpath();
                    String filename = docPath;
                    int index = docPath.lastIndexOf("/");
                    if (index !=-1) {
                        filename = docPath.substring(index+1, docPath.length());
                    }
                    StreamDownloadResource downloadResource = new StreamDownloadResource(inStream, filename, AppContext.getApplication());
                    attachmentLayout.getWindow().open(downloadResource, "_self");
                }
            }
        });
        downloadBtn.setIcon(new ThemeResource("icons/16/download.png"));
        downloadBtn.setStyleName("link");
        attachmentLayout.addComponent(downloadBtn);
        return attachmentLayout;
    }

    public static Component getAttachmentDisplayComponent(String type, int typeid) {
        AttachmentService attachmentService = AppContext.getSpringBean(AttachmentService.class);
        List<Attachment> attachments = attachmentService.findByAttachmentId(type, typeid);
        if (attachments != null && !attachments.isEmpty()) {
            return new AttachmentDisplayComponent(attachments);
        } else {
            return null;
        }
    }
}
