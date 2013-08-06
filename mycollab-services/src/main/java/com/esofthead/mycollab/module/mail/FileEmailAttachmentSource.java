package com.esofthead.mycollab.module.mail;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;

public class FileEmailAttachmentSource implements EmailAttachementSource {
	private File file;

	public FileEmailAttachmentSource(File file) {
		this.file = file;
	}

	@Override
	public EmailAttachment getAttachmentObj() {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(file.getPath());
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setName(file.getName());
		return attachment;
	}

}
