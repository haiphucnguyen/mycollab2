package com.mycollab.ondemand.module.user.esb

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.common.i18n.MailI18nEnum
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.core.utils.DateTimeUtils
import com.mycollab.i18n.LocalizationHelper
import com.mycollab.module.billing.UserStatusConstants
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.mycollab.module.user.domain.User
import com.mycollab.module.user.esb.SendUserEmailVerifyRequestEvent
import com.mycollab.module.user.service.UserService
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
class SendVerifyUserEmailCommand(private val userService: UserService,
                                 private val extMailService: ExtMailService,
                                 private val contentGenerator: IContentGenerator) : GenericCommand() {

    @AllowConcurrentEvents
    @Subscribe
    fun sendVerifyEmailRequest(event: SendUserEmailVerifyRequestEvent) {
        //    sendConfirmEmailToUser(event.user)
        event.user.status = UserStatusConstants.EMAIL_VERIFIED_REQUEST
        userService.updateWithSession(event.user, event.user.username)
    }

    fun sendConfirmEmailToUser(user: User) {
        contentGenerator.putVariable("user", user)
        //    val siteUrl = GenericLinkUtils.generateSiteUrlByAccountId(user.getAccountId)
        //    contentGenerator.putVariable("siteUrl", siteUrl)
        //    val confirmLink = siteUrl + "user/confirm_signup/" + UrlEncodeDecoder.encode(user.getUsername + "/" + user.getAccountId)
        //    contentGenerator.putVariable("linkConfirm", confirmLink)
        contentGenerator.putVariable("copyRight", LocalizationHelper.getMessage(Locale.US, MailI18nEnum.Copyright,
                DateTimeUtils.getCurrentYear()))
        extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getDefaultSiteName(),
                listOf(MailRecipientField(user.email, "${user.firstname} ${user.lastname}")),
                LocalizationHelper.getMessage(Locale.US, UserI18nEnum.MAIL_CONFIRM_PASSWORD_SUBJECT),
                contentGenerator.parseFile("src/main/resources/mailConfirmUserSignUpNotification.ftl", Locale.US))
    }
}