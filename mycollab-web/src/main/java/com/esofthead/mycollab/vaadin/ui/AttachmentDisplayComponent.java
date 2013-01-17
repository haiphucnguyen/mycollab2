/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class AttachmentDisplayComponent extends VerticalLayout {

    public AttachmentDisplayComponent(List<Attachment> attachments) {
        for (Attachment attachment : attachments) {
            String docName = attachment.getDocumentpath();
            int lastIndex = docName.lastIndexOf("/");
            if (lastIndex != -1) {
                docName = docName.substring(lastIndex + 1,
                        docName.length());
            }

            HorizontalLayout attachmentLayout = new HorizontalLayout();
            attachmentLayout.setSpacing(true);
            attachmentLayout.setMargin(false, false, false, true);

            Embedded fileTypeIcon = new Embedded(null, UiUtils.getFileIconResource(docName));
            attachmentLayout.addComponent(fileTypeIcon);
            Label attachmentLink = new Label(docName);
            attachmentLayout.addComponent(attachmentLink);
            attachmentLayout.setComponentAlignment(attachmentLink, Alignment.MIDDLE_CENTER);

            Embedded trashBtn = new Embedded(null, new ThemeResource(
                    "icons/16/trash.png"));
            attachmentLayout.addComponent(trashBtn);

            Embedded downloadBtn = new Embedded(null,
                    new ThemeResource("icons/16/download.png"));
            attachmentLayout.addComponent(downloadBtn);

            this.addComponent(attachmentLayout);
        }
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
