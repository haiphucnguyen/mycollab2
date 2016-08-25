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

import com.mycollab.common.i18n.FileI18nEnum;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.web.ui.AttachmentUploadComponent;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.StreamVariable;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class MultiFileUploadExt extends MultiFileUpload {
    private static final long serialVersionUID = 1L;

    private AttachmentUploadComponent attachmentDisplayComponent;

    public MultiFileUploadExt(AttachmentUploadComponent attachmentDisplayComponent) {
        this.attachmentDisplayComponent = attachmentDisplayComponent;
        attachmentDisplayComponent.registerMultiUpload(this);

    }

    @Override
    protected void handleFile(File file, String fileName, String mimeType, long length) {
        attachmentDisplayComponent.receiveFile(file, fileName, mimeType, length);
    }
}
