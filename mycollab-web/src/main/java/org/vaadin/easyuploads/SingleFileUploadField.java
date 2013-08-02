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
