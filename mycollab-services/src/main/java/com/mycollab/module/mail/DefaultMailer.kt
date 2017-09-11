package com.mycollab.module.mail

import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.EmailConfiguration
import com.mycollab.core.MyCollabException
import com.mycollab.core.utils.StringUtils
import org.apache.commons.collections.CollectionUtils
import org.apache.commons.mail.EmailConstants
import org.apache.commons.mail.EmailException
import org.apache.commons.mail.HtmlEmail
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class DefaultMailer(private val emailConf: EmailConfiguration) : IMailer {

    private fun getBasicEmail(fromEmail: String, fromName: String, toEmail: List<MailRecipientField>,
                              ccEmail: List<MailRecipientField>, bccEmail: List<MailRecipientField>,
                              subject: String, html: String): HtmlEmail {
        try {
            val email = HtmlEmail()
            email.hostName = emailConf.host
            email.setSmtpPort(emailConf.port!!)
            email.isStartTLSEnabled = emailConf.isStartTls
            email.isSSLOnConnect = emailConf.isSsl
            email.setFrom(fromEmail, fromName)
            email.setCharset(EmailConstants.UTF_8)
            for (aToEmail in toEmail) {
                if (isValidate(aToEmail.email) && isValidate(aToEmail.name)) {
                    email.addTo(aToEmail.email, aToEmail.name)
                } else {
                    LOG.error(String.format("Invalid to email input: %s---%s", aToEmail.email, aToEmail.name))
                }
            }

            if (CollectionUtils.isNotEmpty(ccEmail)) {
                for (aCcEmail in ccEmail) {
                    if (isValidate(aCcEmail.email) && isValidate(aCcEmail.name)) {
                        email.addCc(aCcEmail.email, aCcEmail.name)
                    } else {
                        LOG.error(String.format("Invalid cc email input: %s---%s", aCcEmail.email, aCcEmail.name))
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(bccEmail)) {
                for (aBccEmail in bccEmail) {
                    if (isValidate(aBccEmail.email) && isValidate(aBccEmail.name)) {
                        email.addBcc(aBccEmail.email, aBccEmail.name)
                    } else {
                        LOG.error(String.format("Invalid bcc email input: %s---%s", aBccEmail.email, aBccEmail.name))
                    }
                }
            }

            if (emailConf.user != null) {
                email.setAuthentication(emailConf.user, emailConf.password)
            }

            email.subject = subject

            if (StringUtils.isNotBlank(html)) {
                email.setHtmlMsg(html)
            }

            return email
        } catch (e: EmailException) {
            throw MyCollabException(e)
        }

    }

    override fun sendHTMLMail(fromEmail: String, fromName: String, toEmails: List<MailRecipientField>, ccEmails: List<MailRecipientField>,
                     bccEmails: List<MailRecipientField>, subject: String, html: String) {
        try {
            val email = getBasicEmail(fromEmail, fromName, toEmails, ccEmails, bccEmails, subject, html)
            email.send()
        } catch (e: EmailException) {
            throw MyCollabException(e)
        }

    }

    override fun sendHTMLMail(fromEmail: String, fromName: String, toEmails: List<MailRecipientField>,
                     ccEmails: List<MailRecipientField>, bccEmails: List<MailRecipientField>,
                     subject: String, html: String, attachments: List<AttachmentSource>) {
        try {
            if (CollectionUtils.isEmpty(attachments)) {
                sendHTMLMail(fromEmail, fromName, toEmails, ccEmails, bccEmails, subject, html)
            } else {
                val email = getBasicEmail(fromEmail, fromName, toEmails, ccEmails, bccEmails, subject, html)

                for (attachment in attachments) {
                    email.attach(attachment.attachmentObj)
                }

                email.send()
            }
        } catch (e: EmailException) {
            throw MyCollabException(e)
        }

    }

    private fun isValidate(`val`: String): Boolean {
        return StringUtils.isNotBlank(`val`)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(DefaultMailer::class.java)
    }
}
