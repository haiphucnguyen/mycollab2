package com.mycollab.module.mail.service;

import com.mycollab.common.domain.MailRecipientField;
import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.mail.AttachmentSource;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ExtMailService extends IService {
    boolean isMailSetupValid();

    /**
     * @param fromEmail
     * @param fromName
     * @param toEmail
     * @param subject
     * @param html
     */
    void sendHTMLMail(String fromEmail, String fromName, List<MailRecipientField> toEmail, String subject, String html);

    void sendHTMLMail(String fromEmail, String fromName, List<MailRecipientField> toEmail,
                      List<MailRecipientField> ccEmail, List<MailRecipientField> bccEmail, String subject, String html);

    /**
     * @param fromEmail
     * @param fromName
     * @param toEmail
     * @param ccEmail
     * @param bccEmail
     * @param subject
     * @param html
     * @param attachments
     * @param canRetry
     */
    void sendHTMLMail(String fromEmail, String fromName,
                      List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
                      List<MailRecipientField> bccEmail, String subject, String html,
                      List<? extends AttachmentSource> attachments, boolean canRetry);
}
