package com.esofthead.mycollab.module.user.esb.impl

import java.util.Arrays

import com.esofthead.mycollab.common.domain.MailRecipientField
import com.esofthead.mycollab.configuration.{SiteConfiguration, StorageFactory}
import com.esofthead.mycollab.html.{DivLessFormatter, LinkUtils}
import com.esofthead.mycollab.i18n.LocalizationHelper
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.billing.RegisterStatusConstants
import com.esofthead.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.module.user.esb.SendUserInvitationEvent
import com.esofthead.mycollab.module.user.esb.impl.SendUserInvitationCommand.Formatter
import com.esofthead.mycollab.module.user.service.UserService
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.hp.gagawa.java.elements.{Img, Text}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.2.2
  */
object SendUserInvitationCommand {

  class Formatter {
    def formatUser(user: SimpleUser): String = {
      val img: Img = new Img("", StorageFactory.getInstance().getAvatarPath(user.getAvatarid, 16))
      new DivLessFormatter().appendChild(img, DivLessFormatter.EMPTY_SPACE, new Text(user.getDisplayName)).write
    }
  }

}

@Component class SendUserInvitationCommand extends GenericCommand {
  @Autowired var userService: UserService = _
  @Autowired var contentGenerator: IContentGenerator = _
  @Autowired var extMailService: ExtMailService = _

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: SendUserInvitationEvent): Unit = {
    try {
      val inviterUser = userService.findUserByUserNameInAccount(event.inviter, event.sAccountId)
      contentGenerator.putVariable("urlAccept", LinkUtils.generateUserAcceptLink(event.subdomain,
        event.sAccountId, event.invitee))
      contentGenerator.putVariable("userName", event.invitee)
      contentGenerator.putVariable("formatter", new Formatter)
      contentGenerator.putVariable("inviter", inviterUser)
      extMailService.sendHTMLMail(SiteConfiguration.getNoReplyEmail, SiteConfiguration.getDefaultSiteName,
        Arrays.asList(new MailRecipientField(event.invitee, event.invitee)), null, null,
        contentGenerator.parseString(LocalizationHelper.getMessage(SiteConfiguration.getDefaultLocale,
          UserI18nEnum.MAIL_INVITE_USER_SUBJECT, SiteConfiguration.getDefaultSiteName)),
        contentGenerator.parseFile("templates/email/user/userInvitationNotifier.mt",
          SiteConfiguration.getDefaultLocale), null)
      userService.updateUserAccountStatus(event.invitee, event.sAccountId,
        RegisterStatusConstants.SENT_VERIFICATION_EMAIL)
    } catch {
      case e: Exception => {
        userService.updateUserAccountStatus(event.invitee, event.sAccountId, RegisterStatusConstants.VERIFICATING)
      }
    }
  }
}
