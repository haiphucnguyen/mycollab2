package com.mycollab.module.mail;

import com.mycollab.common.domain.MailRecipientField;
import com.mycollab.module.user.domain.SimpleUser;

import java.util.List;

/**
 * Abstract mailer. Note: in MyCollab we now support send HTML content email
 * only.
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface IMailer {
    /**
     * @param fromEmail
     * @param fromName
     * @param toEmails
     * @param ccEmails
     * @param bccEmails
     * @param subject
     * @param html
     */
    void sendHTMLMail(String fromEmail, String fromName, List<MailRecipientField> toEmails, List<MailRecipientField> ccEmails,
                      List<MailRecipientField> bccEmails, String subject, String html);

    /**
     * @param fromEmail
     * @param fromName
     * @param toEmails
     * @param ccEmails
     * @param bccEmails
     * @param subject
     * @param html
     * @param attachments
     */
    void sendHTMLMail(String fromEmail, String fromName, List<MailRecipientField> toEmails, List<MailRecipientField> ccEmails,
                      List<MailRecipientField> bccEmails, String subject, String html, List<? extends AttachmentSource> attachments);
}
