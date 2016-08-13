package com.mycollab.module.user.esb

import java.util.{Arrays, Collections, Locale}

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.i18n.LocalizationHelper
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.mycollab.module.user.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.2
  */
@Component class SendUserInvitationCommand extends GenericCommand {
  private val LOG = LoggerFactory.getLogger(classOf[SendUserInvitationCommand])
  @Autowired var userService: UserService = _
  @Autowired var contentGenerator: IContentGenerator = _
  @Autowired var extMailService: ExtMailService = _
  
  @AllowConcurrentEvents
  @Subscribe
  def execute(event: SendUserInvitationEvent): Unit = {
    val inviteeUser = userService.findUserByUserNameInAccount(event.invitee, event.sAccountId)
    if (inviteeUser != null) {
      contentGenerator.putVariable("siteUrl", SiteConfiguration.getSiteUrl(inviteeUser.getSubdomain))
      contentGenerator.putVariable("invitee", inviteeUser)
      contentGenerator.putVariable("password", event.password)
      extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName,
        Collections.singletonList(new MailRecipientField(event.invitee, event.invitee)),
        LocalizationHelper.getMessage(Locale.US, UserI18nEnum.MAIL_INVITE_USER_SUBJECT, SiteConfiguration.getDefaultSiteName),
        contentGenerator.parseFile("mailUserInvitationNotifier.ftl", Locale.US))
    } else {
      LOG.error("Can not find the user with username %s in account %s".format(event.invitee, event.sAccountId))
    }
  }
}
