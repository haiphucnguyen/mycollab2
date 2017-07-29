package com.mycollab.module.mail.service.impl;

import com.mycollab.common.domain.MailRecipientField;
import com.mycollab.configuration.EmailConfiguration;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.mail.AttachmentSource;
import com.mycollab.module.mail.DefaultMailer;
import com.mycollab.module.mail.IMailer;
import com.mycollab.module.mail.NullMailer;
import com.mycollab.module.mail.service.ExtMailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class ExtMailServiceImpl implements ExtMailService {

    @Override
    public boolean isMailSetupValid() {
        EmailConfiguration emailConfiguration = SiteConfiguration.getEmailConfiguration();
        return StringUtils.isNotBlank(emailConfiguration.getHost()) && StringUtils.isNotBlank(emailConfiguration.getUser())
                && (emailConfiguration.getPort() > -1);
    }

    private IMailer getMailer() {
        EmailConfiguration emailConfiguration = SiteConfiguration.getEmailConfiguration();
        if (!isMailSetupValid()) {
            return new NullMailer();
        }

        return new DefaultMailer(emailConfiguration);
    }

    @Override
    public void sendHTMLMail(String fromEmail, String fromName, List<MailRecipientField> toEmail, String subject, String html) {
        getMailer().sendHTMLMail(fromEmail, fromName, toEmail, null, null, subject, html, null);
    }

    @Override
    public void sendHTMLMail(String fromEmail, String fromName, List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
                             List<MailRecipientField> bccEmail, String subject, String html) {
        getMailer().sendHTMLMail(fromEmail, fromName, toEmail, ccEmail, bccEmail, subject, html, null);
    }

    @Override
    public void sendHTMLMail(String fromEmail, String fromName,
                             List<MailRecipientField> toEmail, List<MailRecipientField> ccEmail,
                             List<MailRecipientField> bccEmail, String subject, String html,
                             List<? extends AttachmentSource> attachments, boolean canRetry) {
        getMailer().sendHTMLMail(fromEmail, fromName, toEmail, ccEmail, bccEmail, subject, html, attachments);
    }
}
