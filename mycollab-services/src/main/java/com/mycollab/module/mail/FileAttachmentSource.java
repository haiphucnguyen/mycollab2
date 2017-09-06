package com.mycollab.module.mail;

import com.mycollab.core.MyCollabException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailAttachment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class FileAttachmentSource implements AttachmentSource {
    private File file;
    private String name;

    public FileAttachmentSource(File file) {
        this(null, file);
    }

    public FileAttachmentSource(String name, File file) {
        this.file = file;
        this.name = name;
    }

    public FileAttachmentSource(String name, InputStream inputStream) {
        this.name = name;
        try {
            this.file = File.createTempFile("mycollab", "tmp");
            FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (IOException e) {
            throw new MyCollabException(e);
        }
    }

    @Override
    public EmailAttachment getAttachmentObj() {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(file.getPath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName((name == null) ? file.getName() : name);
        return attachment;
    }
}
