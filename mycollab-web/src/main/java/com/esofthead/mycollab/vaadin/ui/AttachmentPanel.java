package com.esofthead.mycollab.vaadin.ui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ImageUtil;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AttachmentPanel extends VerticalLayout implements
		AttachmentUploadComponent {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(AttachmentPanel.class);
	private Map<String, File> fileStores;

	private MultiFileUploadExt multiFileUpload;
	private ResourceService resourceService;

	public AttachmentPanel() {
		resourceService = AppContext.getSpringBean(ResourceService.class);
		this.setSpacing(true);
	}

	public void registerMultiUpload(MultiFileUploadExt fileUpload) {
		multiFileUpload = fileUpload;
	}

	private void displayFileName(final String fileName) {
		final HorizontalLayout fileAttachmentLayout = new HorizontalLayout();
		fileAttachmentLayout.setSpacing(true);
		Button removeBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				File file = fileStores.get(fileName);
				if (file != null) {
					file.delete();
				}
				fileStores.remove(fileName);
				AttachmentPanel.this.removeComponent(fileAttachmentLayout);
				if (multiFileUpload != null) {
					multiFileUpload.removeAndReInitMultiUpload();
				}
			}
		});
		removeBtn.setIcon(MyCollabResource.newResource("icons/16/trash.png"));
		removeBtn.setStyleName("link");

		Embedded fileIcon = new Embedded(null,
				UiUtils.getFileIconResource(fileName));
		fileAttachmentLayout.addComponent(fileIcon);

		Label fileLbl = new Label(fileName);
		fileAttachmentLayout.addComponent(fileLbl);
		fileAttachmentLayout.setComponentAlignment(fileLbl,
				Alignment.MIDDLE_CENTER);
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

		List<Content> attachments = resourceService.getContents(AttachmentUtils
				.getAttachmentPath(AppContext.getAccountId(), type, typeid));
		if (attachments != null && !attachments.isEmpty()) {
			for (final Content attachment : attachments) {
				this.addComponent(AttachmentDisplayComponent
						.constructAttachmentRow(attachment));
			}
		}
	}

	public void saveContentsToRepo(String type, Integer typeid) {
		if (fileStores != null && !fileStores.isEmpty()) {

			for (String fileName : fileStores.keySet()) {
				String attachmentPath = AttachmentUtils.getAttachmentPath(
						AppContext.getAccountId(), type, typeid);
				try {
					String fileExt = "";
					int index = fileName.lastIndexOf(".");
					if (index > 0) {
						fileExt = fileName.substring(index + 1,
								fileName.length());
					}

					if ("jpg".equalsIgnoreCase(fileExt)
							|| "png".equalsIgnoreCase(fileExt)) {
						try {
							BufferedImage bufferedImage = ImageIO
									.read(fileStores.get(fileName));

							int imgHeight = bufferedImage.getHeight();
							int imgWidth = bufferedImage.getWidth();

							BufferedImage scaledImage = null;

							float scale;
							float destWidth = 974;
							float destHeight = 718;

							float scaleX = Math.min(destHeight / imgHeight, 1);
							float scaleY = Math.min(destWidth / imgWidth, 1);
							scale = Math.min(scaleX, scaleY);
							scaledImage = ImageUtil.scaleImage(bufferedImage,
									scale);

							ByteArrayOutputStream outStream = new ByteArrayOutputStream();
							ImageIO.write(scaledImage, fileExt, outStream);

							resourceService.saveContent(
									constructContent(fileName, attachmentPath),
									AppContext.getUsername(),
									new ByteArrayInputStream(outStream
											.toByteArray()));
						} catch (IOException e) {
							e.printStackTrace();
							resourceService.saveContent(
									constructContent(fileName, attachmentPath),
									AppContext.getUsername(),
									new FileInputStream(fileStores
											.get(fileName)));
						}
					} else {
						resourceService.saveContent(
								constructContent(fileName, attachmentPath),
								AppContext.getUsername(), new FileInputStream(
										fileStores.get(fileName)));
					}

				} catch (FileNotFoundException e) {
					log.error("Error when attach content in UI", e);
				}
			}
		}
	}

	private Content constructContent(String fileName, String path) {
		Content content = new Content();
		content.setPath(path);
		content.setTitle(fileName);
		content.setDescription("");
		return content;
	}

	public List<File> getListFile() {
		List<File> listFile = null;
		if (fileStores != null && fileStores.size() > 0) {
			listFile = new ArrayList<File>();
			for (String fileName : fileStores.keySet()) {
				File oldFile = fileStores.get(fileName);
				File parentFile = oldFile.getParentFile();
				File newFile = new File(parentFile, fileName);
				if (newFile.exists())
					newFile.delete();
				if (oldFile.renameTo(newFile)) {
					listFile.add(newFile);
				}

				if (listFile.size() <= 0)
					return null;

			}
		}
		return listFile;
	}

	@Override
	public void receiveFile(File file, String fileName, String mimeType,
			long length) {
		if (fileStores == null) {
			fileStores = new HashMap<String, File>();
		}
		if (fileStores.containsKey(fileName)) {
			getWindow().showNotification(
					"File name " + fileName + " is already existed.");
		} else {
			log.debug("Store file " + fileName + " in path "
					+ file.getAbsolutePath() + " is exist: " + file.exists());
			fileStores.put(fileName, file);
			displayFileName(fileName);
		}
	}
}
