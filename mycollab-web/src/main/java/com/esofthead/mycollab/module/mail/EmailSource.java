package com.esofthead.mycollab.module.mail;

import org.apache.commons.mail.EmailAttachment;

public interface EmailSource {
	EmailAttachment getAttachmentObj();
}
