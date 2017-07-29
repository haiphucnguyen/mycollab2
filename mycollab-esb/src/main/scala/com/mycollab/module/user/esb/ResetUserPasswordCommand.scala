package com.mycollab.module.user.esb

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.common.UrlEncodeDecoder
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.common.i18n.MailI18nEnum
import com.mycollab.configuration.{IDeploymentMode, SiteConfiguration}
import com.mycollab.core.utils.DateTimeUtils
import com.mycollab.i18n.LocalizationHelper
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.mycollab.module.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @version 5.2.5
  */
@Component class ResetUserPasswordCommand extends GenericCommand {
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val userService: UserService = null
  @Autowired private val contentGenerator: IContentGenerator = null
  @Autowired private val deploymentMode: IDeploymentMode = null

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: RequestToResetPasswordEvent): Unit = {
    val username = event.username
    if (username != null) {
      val user = userService.findUserByUserName(username)
      val subDomain = "api"
      val recoveryPasswordURL = deploymentMode.getSiteUrl(subDomain) + "user/recoverypassword/" +
        UrlEncodeDecoder.encode(username)
      val locale = LocalizationHelper.getLocaleInstance(user.getLanguage)
      contentGenerator.putVariable("username", user.getUsername)
      contentGenerator.putVariable("urlRecoveryPassword", recoveryPasswordURL)
      contentGenerator.putVariable("copyRight", LocalizationHelper.getMessage(locale, MailI18nEnum.Copyright,
        DateTimeUtils.getCurrentYear))
      val recipient = new MailRecipientField(user.getEmail, user.getUsername)
      val recipientFields = List[MailRecipientField](recipient)
      import collection.JavaConverters._
      extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
        recipientFields.asJava,
        LocalizationHelper.getMessage(locale, UserI18nEnum.MAIL_RECOVERY_PASSWORD_SUBJECT,
          SiteConfiguration.getDefaultSiteName),
        contentGenerator.parseFile("mailUserRecoveryPasswordNotifier.ftl", locale))
    }
  }
}
