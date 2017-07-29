package com.mycollab.module.mail;

import com.mycollab.common.domain.MailRecipientField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Dummy a mailer in case email configuration not properly set.
 *
 * @author MyCollab Ltd
 * @since 5.0.9
 */
public class NullMailer implements IMailer {
    private static final Logger LOG = LoggerFactory.getLogger(NullMailer.class);

    @Override
    public void sendHTMLMail(String fromEmail, String fromName, List<MailRecipientField> toEmails, List<MailRecipientField> ccEmails,
                             List<MailRecipientField> bccEmails, String subject, String html) {
        LOG.info("You has not configured the email server. So Email feature is disable");
    }

    @Override
    public void sendHTMLMail(String fromEmail, String fromName, List<MailRecipientField> toEmails, List<MailRecipientField> ccEmails,
                             List<MailRecipientField> bccEmails, String subject, String html,
                             List<? extends AttachmentSource> attachments) {
        LOG.info("You has not configured the email server. So Email feature is disable");
    }

}
