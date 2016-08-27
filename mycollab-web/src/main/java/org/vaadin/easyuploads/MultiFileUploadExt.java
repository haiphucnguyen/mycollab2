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
package org.vaadin.easyuploads;

import com.mycollab.vaadin.web.ui.AttachmentPanel;

import java.io.File;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class MultiFileUploadExt extends MultiFileUpload {
    private static final long serialVersionUID = 1L;

    private AttachmentPanel attachmentDisplayComponent;

    public MultiFileUploadExt(AttachmentPanel attachmentDisplayComponent) {
        this.attachmentDisplayComponent = attachmentDisplayComponent;
//        attachmentDisplayComponent.registerMultiUpload(this);

    }

    @Override
    protected void handleFile(File file, String fileName, String mimeType, long length) {
//        attachmentDisplayComponent.receiveFile(file, fileName, mimeType, length);
    }
}
