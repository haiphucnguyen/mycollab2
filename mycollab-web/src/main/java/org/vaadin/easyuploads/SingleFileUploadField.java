package org.vaadin.easyuploads;

import com.vaadin.ui.Label;

public class SingleFileUploadField extends UploadField {
	private static final long serialVersionUID = 1L;

	public SingleFileUploadField() {
		super(StorageMode.FILE);
		this.setFileFactory(new TempFileFactory());

	}

	@Override
	protected void updateDisplay() {
		// TODO Auto-generated method stub

		String filename = getLastFileName();
		String mimeType = getLastMimeType();
		long filesize = getLastFileSize();

		getRootLayout().addComponent(new Label(filename));
		upload.setVisible(false);
	}

	public String getFileName() {
		return getLastFileName();
	}
}
