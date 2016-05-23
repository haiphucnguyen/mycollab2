package com.esofthead.mycollab.module.user.esb.impl

import java.util.{Arrays, Locale}

import com.esofthead.mycollab.common.domain.MailRecipientField
import com.esofthead.mycollab.configuration.SiteConfiguration
import com.esofthead.mycollab.html.LinkUtils
import com.esofthead.mycollab.i18n.LocalizationHelper
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.billing.RegisterStatusConstants
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.esofthead.mycollab.module.user.esb.SendUserInvitationEvent
import com.esofthead.mycollab.module.user.service.UserService
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.2
  */
@Component class SendUserInvitationCommand extends GenericCommand {
  @Autowired var userService: UserService = _
  @Autowired var contentGenerator: IContentGenerator = _
  @Autowired var extMailService: ExtMailService = _

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: SendUserInvitationEvent): Unit = {
    try {
      val inviteeUser = userService.findUserByUserNameInAccount(event.invitee, event.sAccountId)
      contentGenerator.putVariable("urlAccept", LinkUtils.generateUserAcceptLink(event.subdomain,
        event.sAccountId, event.invitee))
      contentGenerator.putVariable("siteUrl", SiteConfiguration.getSiteUrl(inviteeUser.getSubdomain))
      contentGenerator.putVariable("invitee", inviteeUser)
      contentGenerator.putVariable("password", event.password)
      extMailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getDefaultSiteName,
        Arrays.asList(new MailRecipientField(event.invitee, event.invitee)), null, null,
        contentGenerator.parseString(LocalizationHelper.getMessage(Locale.US,
          UserI18nEnum.MAIL_INVITE_USER_SUBJECT, SiteConfiguration.getDefaultSiteName)),
        contentGenerator.parseFile("templates/email/user/userInvitationNotifier.mt",
          Locale.US), null)
      if (inviteeUser.getRegisterstatus != RegisterStatusConstants.ACTIVE) {
        userService.updateUserAccountStatus(event.invitee, event.sAccountId,
          RegisterStatusConstants.SENT_VERIFICATION_EMAIL)
      }
    } catch {
      case e: Exception => {
        userService.updateUserAccountStatus(event.invitee, event.sAccountId, RegisterStatusConstants.VERIFICATING)
      }
    }
  }
}
