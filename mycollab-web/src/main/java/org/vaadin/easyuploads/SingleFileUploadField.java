package org.vaadin.easyuploads;

import com.vaadin.ui.Label;
import com.vaadin.ui.Upload.FinishedEvent;

public class SingleFileUploadField extends UploadField {
	private static final long serialVersionUID = 1L;

	public SingleFileUploadField() {
		super(StorageMode.FILE);
		this.setFileFactory(new TempFileFactory());
		
		
	}
	
	@Override
	public void uploadFinished(FinishedEvent event) {
		Object source = event.getSource();
		System.out.println("Source: " + source);
		super.uploadFinished(event);
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
}
