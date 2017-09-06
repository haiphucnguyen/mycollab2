package com.mycollab.module.mail;

import org.apache.commons.mail.EmailAttachment;

import java.net.URL;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public class UrlAttachmentSource implements AttachmentSource {
    private String name;
    private URL url;

    public UrlAttachmentSource(String name, URL url) {
        this.url = url;
        this.name = name;
    }

    @Override
    public EmailAttachment getAttachmentObj() {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setURL(url);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName(name);
        return attachment;
    }
}
