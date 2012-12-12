package com.esofthead.mycollab.module.crm.ui.components;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.easyuploads.MultiFileUpload;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AttachmentPanel extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private Map<String, File> fileStores;

	public AttachmentPanel() {
		MultiFileUpload multiFileUpload = new MultiFileUpload() {
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
					fileStores.put(fileName, file);
					displayFileName(fileName);
				}
			}
		};
		multiFileUpload.setCaption("Attachments");
		this.addComponent(multiFileUpload);
	}
	
	private void displayFileName(String fileName) {
		this.addComponent(new Label(fileName));
	}
}
