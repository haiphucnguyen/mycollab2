package com.esofthead.mycollab.module.crm.ui.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.easyuploads.FileBuffer;
import org.vaadin.easyuploads.MultiFileUpload;

import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.file.service.ContentService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AttachmentPanel extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(AttachmentPanel.class);

	private Map<String, File> fileStores;

	private ContentService contentService;
	private AttachmentService attachmentService;

	private MultiFileUpload multiFileUpload;

	public AttachmentPanel() {

		contentService = AppContext.getSpringBean(ContentService.class);

		multiFileUpload = new MultiFileUpload() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void handleFile(File file, String fileName,
					String mimeType, long length) {
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

			@Override
			protected FileBuffer createReceiver() {
				FileBuffer receiver = super.createReceiver();
				/*
				 * Make receiver not to delete files after they have been
				 * handled by #handleFile().
				 */
				receiver.setDeleteFiles(false);
				return receiver;
			}
		};
		multiFileUpload.setCaption("Attachments");
		this.addComponent(multiFileUpload);
	}

	private void displayFileName(String fileName) {
		this.addComponent(new Label(fileName));
	}

	public void saveContentsToRepo(String type, Integer typeid) {
		if (fileStores != null) {
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
}
