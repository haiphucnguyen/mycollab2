package com.mycollab.module.user.esb

import java.util.Locale

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.hp.gagawa.java.elements.A
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.common.i18n.MailI18nEnum
import com.mycollab.configuration.{EmailConfiguration, IDeploymentMode, SiteConfiguration}
import com.mycollab.core.utils.DateTimeUtils
import com.mycollab.db.arguments._
import com.mycollab.html.LinkUtils
import com.mycollab.i18n.LocalizationHelper
import com.mycollab.module.billing.RegisterStatusConstants
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.mail.service.{ExtMailService, IContentGenerator}
import com.mycollab.module.user.AccountLinkGenerator
import com.mycollab.module.user.domain.SimpleUser
import com.mycollab.module.user.domain.criteria.UserSearchCriteria
import com.mycollab.module.user.esb.NewUserJoinCommand.Formatter
import com.mycollab.module.user.service.{BillingAccountService, UserService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import scala.collection.mutable.ListBuffer

/**
  * @author MyCollab Ltd
  * @since 5.2.6
  */
object NewUserJoinCommand {

  class Formatter {
    def formatMemberLink(siteUrl: String, newMember: SimpleUser): String = {
      new A(AccountLinkGenerator.generatePreviewFullUserLink(siteUrl, newMember.getUsername)).
        appendText(newMember.getDisplayName).write()
    }

    def formatRoleName(siteUrl: String, newMember: SimpleUser): String = {
      if (newMember.getIsAccountOwner == true) "Account Owner"
      else new A(AccountLinkGenerator.generatePreviewFullRoleLink(siteUrl, newMember.getRoleid)).appendText(newMember.getRoleName).write()
    }
  }

}

@Component class NewUserJoinCommand extends GenericCommand {
  @Autowired private val billingAccountService: BillingAccountService = null
  @Autowired private val emailConfiguration: EmailConfiguration = null
  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val contentGenerator: IContentGenerator = null
  @Autowired private val userService: UserService = null
  @Autowired private val deploymentMode: IDeploymentMode = null

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: NewUserJoinEvent): Unit = {
    val username = event.username
    val sAccountId = event.sAccountId
    val searchCriteria = new UserSearchCriteria
    searchCriteria.setSaccountid(new NumberSearchField(sAccountId))
    searchCriteria.setRegisterStatuses(new SetSearchField[String](RegisterStatusConstants.ACTIVE))
    searchCriteria.addExtraField(new OneValueSearchField(SearchField.AND, "s_user_account.isAccountOwner = ", 1))
    import scala.collection.JavaConverters._
    val accountOwners = userService.findPageableListByCriteria(new BasicSearchRequest[UserSearchCriteria](searchCriteria)).asScala.toList
    val newUser = userService.findUserInAccount(username, sAccountId)
    val recipients = ListBuffer[MailRecipientField]()
    accountOwners.foreach(user => {
      val inst = user.asInstanceOf[SimpleUser]
      recipients.append(new MailRecipientField(inst.getUsername, inst.getDisplayName))
    })
    val account = billingAccountService.getAccountById(sAccountId)
    contentGenerator.putVariable("siteUrl", deploymentMode.getSiteUrl(account.getSubdomain))
    contentGenerator.putVariable("newUser", newUser)
    contentGenerator.putVariable("formatter", new Formatter)
    contentGenerator.putVariable("copyRight", LocalizationHelper.getMessage(Locale.US, MailI18nEnum.Copyright,
      DateTimeUtils.getCurrentYear))
    contentGenerator.putVariable("logoPath", LinkUtils.accountLogoPath(account.getId, account.getLogopath))
    extMailService.sendHTMLMail(emailConfiguration.getNotifyEmail, SiteConfiguration.getDefaultSiteName, recipients.asJava,
      String.format("%s has just joined on MyCollab workspace", newUser.getDisplayName),
      contentGenerator.parseFile("mailNewUserJoinAccountNotifier.ftl", Locale.US))
  }
}
