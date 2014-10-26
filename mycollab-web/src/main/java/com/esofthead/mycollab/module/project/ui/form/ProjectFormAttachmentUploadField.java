package com.esofthead.mycollab.module.project.ui.form;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.vaadin.AppContext;
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
public class ProjectFormAttachmentUploadField extends CustomField {
	private static final long serialVersionUID = 1L;
	private MultiFileUploadExt uploadExt;
	private AttachmentPanel attachmentPanel;

	public ProjectFormAttachmentUploadField() {
		attachmentPanel = new AttachmentPanel();
	}

	public void getAttachments(int projectId, AttachmentType type,
			int typeid) {
		String attachmentPath = AttachmentUtils
				.getProjectEntityAttachmentPath(AppContext.getAccountId(),
						projectId, type, typeid);
		attachmentPanel.getAttachments(attachmentPath);
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}

	public void saveContentsToRepo(int projectid, AttachmentType type,
			int typeId) {
		String attachmentPath = AttachmentUtils
				.getProjectEntityAttachmentPath(AppContext.getAccountId(),
						projectid, type, typeId);
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
