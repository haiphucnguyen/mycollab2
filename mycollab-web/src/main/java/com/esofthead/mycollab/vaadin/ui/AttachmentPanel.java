package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.file.service.ContentService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttachmentPanel extends VerticalLayout implements AttachmentUploadComponent {

    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(AttachmentPanel.class);
    private Map<String, File> fileStores;
    private ContentService contentService;
    private AttachmentService attachmentService;

    public AttachmentPanel() {
        contentService = AppContext.getSpringBean(ContentService.class);
        this.setSpacing(true);
    }

    private void displayFileName(final String fileName) {
        final HorizontalLayout fileAttachmentLayout = new HorizontalLayout();
        fileAttachmentLayout.setSpacing(true);
        Button removeBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                File file = fileStores.get(fileName);
                if (file != null) {
                    file.delete();
                }
                fileStores.remove(fileName);
                AttachmentPanel.this.removeComponent(fileAttachmentLayout);
            }
        });
        removeBtn.setIcon(new ThemeResource("icons/16/trash.png"));
        removeBtn.setStyleName("link");

        Embedded fileIcon = new Embedded(null, UiUtils.getFileIconResource(fileName));
        fileAttachmentLayout.addComponent(fileIcon);

        Label fileLbl = new Label(fileName);
        fileAttachmentLayout.addComponent(fileLbl);
        fileAttachmentLayout.setComponentAlignment(fileLbl, Alignment.MIDDLE_CENTER);
        fileAttachmentLayout.addComponent(removeBtn);
        this.addComponent(fileAttachmentLayout, 0);
    }

    public void removeAllAttachmentsDisplay() {
        this.removeAllComponents();
        if (fileStores != null) {
            fileStores.clear();
        }
    }

    public void getAttachments(String type, int typeid) {
        attachmentService = AppContext.getSpringBean(AttachmentService.class);
        List<Attachment> attachments = attachmentService.findByAttachmentId(type, typeid);
        if (attachments != null && !attachments.isEmpty()) {
            for (final Attachment attachment : attachments) {
                this.addComponent(AttachmentDisplayComponent.constructAttachmentRow(attachment));
            }
        }
    }

    public void saveContentsToRepo(String type, Integer typeid) {
        if (fileStores != null && !fileStores.isEmpty()) {
            attachmentService = AppContext
                    .getSpringBean(AttachmentService.class);

            for (String fileName : fileStores.keySet()) {
                String filePath = type + "/" + typeid + "/" + fileName;
                try {
                    contentService.saveContent(AppContext.getAccountId(),
                            filePath,
                            new FileInputStream(fileStores.get(fileName)));
                    Attachment record = new Attachment();
                    record.setType(type);
                    record.setTypeid(typeid);
                    record.setDocumentpath(filePath);
                    attachmentService.saveWithSession(record,
                            AppContext.getUsername());
                } catch (FileNotFoundException e) {
                    log.error("Error when attach content in UI", e);
                }
            }
        }
    }

    @Override
    public void receiveFile(File file, String fileName, String mimeType, long length) {
        if (fileStores == null) {
            fileStores = new HashMap<String, File>();
        }

        if (fileStores.containsKey(fileName)) {
            getWindow().showNotification(
                    "File name " + fileName + " is already existed.");
        } else {
            log.debug("Store file " + fileName + " in path "
                    + file.getAbsolutePath() + " is exist: "
                    + file.exists());
            fileStores.put(fileName, file);
            displayFileName(fileName);
        }
    }
}
