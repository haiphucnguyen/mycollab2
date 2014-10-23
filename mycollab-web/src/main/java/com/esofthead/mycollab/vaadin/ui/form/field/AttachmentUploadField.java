package com.esofthead.mycollab.vaadin.ui.form.field;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
@SuppressWarnings("rawtypes")
public class AttachmentUploadField extends CustomField {
	private static final long serialVersionUID = 1L;
	private MultiFileUploadExt uploadExt;
	private AttachmentPanel attachmentPanel;

	public AttachmentUploadField() {
		attachmentPanel = new AttachmentPanel();
	}

	public void getAttachments(String attachmentPath) {
		attachmentPanel.getAttachments(attachmentPath);
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}

	public void saveContentsToRepo(String attachmentPath) {
		attachmentPanel.saveContentsToRepo(attachmentPath);
	}

	@Override
	protected Component initContent() {
		final VerticalLayout layout = new VerticalLayout();
		uploadExt = new MultiFileUploadExt(attachmentPanel);
		uploadExt.addComponent(attachmentPanel);
		layout.addComponent(uploadExt);
		return layout;
	}
}
