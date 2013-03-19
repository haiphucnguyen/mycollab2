package com.esofthead.mycollab.module.mail;

import org.apache.commons.mail.EmailAttachment;

public class S3EmailAttachmentSource implements EmailSource {

	public S3EmailAttachmentSource(String path) {

	}

	@Override
	public EmailAttachment getAttachmentObj() {
		return null;
	}

}
