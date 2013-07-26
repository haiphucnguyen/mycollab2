package com.esofthead.mycollab.module.project.ui.components;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultProjectFormViewFieldFactory {
	public static class ProjectFormAttachmentDisplayField extends CustomField {
		private static final long serialVersionUID = 1L;

		public ProjectFormAttachmentDisplayField(final int projectid,
				final String type, final int typeid) {
			final Component comp = ProjectAttachmentDisplayComponentFactory
					.getAttachmentDisplayComponent(projectid, type, typeid);
			if (comp == null || !(comp instanceof AttachmentDisplayComponent)) {
				final Label l = new Label("&nbsp;", Label.CONTENT_XHTML);
				setCompositionRoot(l);
			} else {
				setCompositionRoot(comp);
			}
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}
	}

	public static class ProjectFormAttachmentUploadField extends CustomField {
		private static final long serialVersionUID = 1L;
		private final MultiFileUploadExt uploadExt;
		private final ProjectAttachmentPanel attachmentPanel;

		public ProjectFormAttachmentUploadField() {
			final VerticalLayout layout = new VerticalLayout();
			attachmentPanel = new ProjectAttachmentPanel();
			uploadExt = new MultiFileUploadExt(attachmentPanel);
			layout.addComponent(attachmentPanel);
			layout.addComponent(uploadExt);
			setCompositionRoot(layout);
		}

		public void getAttachments(int projectId, final String type,
				final int typeid) {
			attachmentPanel.getAttachments(projectId, type, typeid);
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}

		public void saveContentsToRepo(int projectid, final String type,
				final int typeId) {
			attachmentPanel.saveContentsToRepo(projectid, type, typeId);
		}
	}
}
