package com.mycollab.ondemand.module.user.esb

import java.util.{Arrays, Locale}

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.common.i18n.MailI18nEnum
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.core.utils.DateTimeUtils
import com.mycollab.i18n.LocalizationHelper
import com.mycollab.module.billing.UserStatusConstants
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.mycollab.module.user.domain.User
import com.mycollab.module.user.esb.SendUserEmailVerifyRequestEvent
import com.mycollab.module.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.4.0
  */
@Component class SendVerifyUserEmailCommandImpl extends GenericCommand {
  @Autowired private val userService: UserService = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val contentGenerator: IContentGenerator = null

  @AllowConcurrentEvents
  @Subscribe
  def sendVerifyEmailRequest(event: SendUserEmailVerifyRequestEvent): Unit = {
    //    sendConfirmEmailToUser(event.user)
    event.user.setStatus(UserStatusConstants.EMAIL_VERIFIED_REQUEST)
    userService.updateWithSession(event.user, event.user.getUsername)
  }

  def sendConfirmEmailToUser(user: User) {
    contentGenerator.putVariable("user", user)
    //    val siteUrl = GenericLinkUtils.generateSiteUrlByAccountId(user.getAccountId)
    //    contentGenerator.putVariable("siteUrl", siteUrl)
    //    val confirmLink = siteUrl + "user/confirm_signup/" + UrlEncodeDecoder.encode(user.getUsername + "/" + user.getAccountId)
    //    contentGenerator.putVariable("linkConfirm", confirmLink)
    contentGenerator.putVariable("copyRight", LocalizationHelper.getMessage(Locale.US, MailI18nEnum.Copyright,
      DateTimeUtils.getCurrentYear))
    extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
      Arrays.asList(new MailRecipientField(user.getEmail, user.getFirstname + " " + user.getLastname)),
      LocalizationHelper.getMessage(Locale.US, UserI18nEnum.MAIL_CONFIRM_PASSWORD_SUBJECT),
      contentGenerator.parseFile("src/main/resources/mailConfirmUserSignUpNotification.ftl", Locale.US))
  }
}
