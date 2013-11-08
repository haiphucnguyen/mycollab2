/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.ui.components;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultProjectFormViewFieldFactory {
	public static class ProjectFormAttachmentDisplayField extends CustomField {
		private static final long serialVersionUID = 1L;

		public ProjectFormAttachmentDisplayField(final int projectid,
				final AttachmentType type, final int typeid) {
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
		private final AttachmentPanel attachmentPanel;

		public ProjectFormAttachmentUploadField() {
			final VerticalLayout layout = new VerticalLayout();
			attachmentPanel = new AttachmentPanel();
			uploadExt = new MultiFileUploadExt(attachmentPanel);
			layout.addComponent(attachmentPanel);
			layout.addComponent(uploadExt);
			setCompositionRoot(layout);
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
	}
}
