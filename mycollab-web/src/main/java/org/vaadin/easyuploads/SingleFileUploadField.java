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

import java.io.File;

import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class SingleFileUploadField extends UploadField {
	private static final long serialVersionUID = 1L;

	public SingleFileUploadField() {
		super(StorageMode.FILE);
		this.setFileFactory(new TempFileFactory());
	}

	public File getContentAsFile() {
		if (receiver instanceof FileBuffer) {
			return ((FileBuffer) receiver).getFile();
		}
		return null;
	}

	@Override
	protected void updateDisplay() {
		// super.updateDisplay();
		String filename = getLastFileName();

		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(new Embedded(null, UiUtils
				.getFileIconResource(filename)));
		layout.addComponent(new Label(filename));

		getRootLayout().addComponent(layout);
		upload.setVisible(false);
	}

	public String getFileName() {
		return getLastFileName();
	}

	public long getFileSize() {
		return getLastFileSize();
	}
}
