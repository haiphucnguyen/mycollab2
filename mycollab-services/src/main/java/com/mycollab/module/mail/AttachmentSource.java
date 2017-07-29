package com.mycollab.module.mail;

import org.apache.commons.mail.EmailAttachment;

/**
 * Attachment source of email
 *
 * @author MyCollab Ltd
 * @since 1.0
 */
public interface AttachmentSource {
    EmailAttachment getAttachmentObj();
}
