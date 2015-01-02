package com.esofthead.mycollab.schedule.email.crm.impl

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.configuration.StorageManager
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.module.crm.domain.{CaseWithBLOBs, SimpleAccount, SimpleCase}
import com.esofthead.mycollab.module.crm.i18n.CaseI18nEnum
import com.esofthead.mycollab.module.crm.service.{AccountService, CaseService}
import com.esofthead.mycollab.module.crm.{CrmLinkGenerator, CrmResources, CrmTypeConstants}
import com.esofthead.mycollab.module.mail.MailUtils
import com.esofthead.mycollab.module.user.AccountLinkGenerator
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.module.user.service.UserService
import com.esofthead.mycollab.schedule.email.crm.CaseRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.format.{TagBuilder, FieldFormat}
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.hp.gagawa.java.elements.{A, Img, Span}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CaseRelayEmailNotificationActionImpl extends CrmDefaultSendingRelayEmailAction[SimpleCase] with CaseRelayEmailNotificationAction{
  private val LOG = LoggerFactory.getLogger(classOf[CaseRelayEmailNotificationActionImpl])
  @Autowired var caseService: CaseService = _
  private val mapper = new CaseFieldNameMapper

  override protected def getBeanInContext(context: MailContext[SimpleCase]): SimpleCase = caseService.findById(context.getTypeid.toInt, context.getSaccountid)

  override protected def getCreateSubjectKey: Enum[_] = CaseI18nEnum.MAIL_CREATE_ITEM_SUBJECT

  override protected def getCommentSubjectKey: Enum[_] = CaseI18nEnum.MAIL_COMMENT_ITEM_SUBJECT

  override protected def getItemFieldMapper: ItemFieldMapper = mapper

  override protected def getItemName: String = StringUtils.trim(bean.getSubject, 100)

  override protected def buildExtraTemplateVariables(context: MailContext[SimpleCase]): Unit = {
    val summary: String = bean.getSubject
    val summaryLink: String = CrmLinkGenerator.generateCasePreviewFullLink(siteUrl, bean.getId)

    val emailNotification: SimpleRelayEmailNotification = context.getEmailNotification

    var avatarId: String = ""

    val user: SimpleUser = userService.findUserByUserNameInAccount(emailNotification.getChangeby, context.getSaccountid)

    if (user != null) {
      avatarId = user.getAvatarid
    }
    val userAvatar: Img = new Img("", StorageManager.getAvatarLink(avatarId, 16))
    userAvatar.setWidth("16")
    userAvatar.setHeight("16")
    userAvatar.setStyle("display: inline-block; vertical-align: top;")

    val makeChangeUser: String = userAvatar.toString + emailNotification.getChangeByUserFullName

    if (MonitorTypeConstants.CREATE_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(CaseI18nEnum.MAIL_CREATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.UPDATE_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(CaseI18nEnum.MAIL_UPDATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.ADD_COMMENT_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(CaseI18nEnum.MAIL_COMMENT_ITEM_HEADING, makeChangeUser))
    }

    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
  }

  override protected def getUpdateSubjectKey: Enum[_] = CaseI18nEnum.MAIL_UPDATE_ITEM_SUBJECT

  class CaseFieldNameMapper extends ItemFieldMapper {
      put(CaseWithBLOBs.Field.subject, CaseI18nEnum.FORM_SUBJECT, true)
      put(CaseWithBLOBs.Field.description, GenericI18Enum.FORM_DESCRIPTION)
      put(CaseWithBLOBs.Field.accountid, new AccountFieldFormat(CaseWithBLOBs.Field.accountid.name, CaseI18nEnum
        .FORM_ACCOUNT))
      put(CaseWithBLOBs.Field.priority, CaseI18nEnum.FORM_PRIORITY)
      put(CaseWithBLOBs.Field.`type`, CaseI18nEnum.FORM_TYPE)
      put(CaseWithBLOBs.Field.status, CaseI18nEnum.FORM_STATUS)
      put(CaseWithBLOBs.Field.reason, CaseI18nEnum.FORM_REASON)
      put(CaseWithBLOBs.Field.phonenumber, CaseI18nEnum.FORM_PHONE)
      put(CaseWithBLOBs.Field.email, CaseI18nEnum.FORM_EMAIL)
      put(CaseWithBLOBs.Field.origin, CaseI18nEnum.FORM_ORIGIN)
      put(CaseWithBLOBs.Field.assignuser, new AssigneeFieldFormat(CaseWithBLOBs.Field.assignuser.name, GenericI18Enum.FORM_ASSIGNEE))
      put(CaseWithBLOBs.Field.resolution, CaseI18nEnum.FORM_RESOLUTION, true)
  }

  class AccountFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val simpleCase: SimpleCase = context.getWrappedBean.asInstanceOf[SimpleCase]
      if (simpleCase.getAccountid != null) {
        val accountIconLink: String = CrmResources.getResourceLink(CrmTypeConstants.ACCOUNT)
        val img: Img = TagBuilder.newImg("avatar", accountIconLink)
        val accountLink: String = CrmLinkGenerator.generateAccountPreviewFullLink(context.siteUrl, simpleCase.getAccountid)
        val link: A = TagBuilder.newA(accountLink, simpleCase.getAccountName)
         TagBuilder.newLink(img, link).write
      }
      else {
         new Span().write
      }
    }

    def formatField(context: MailContext[_], value: String): String = {
      if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
         new Span().write
      }
      try {
        val accountId: Integer = value.toInt
        val accountService: AccountService = ApplicationContextUtil.getSpringBean(classOf[AccountService])
        val account: SimpleAccount = accountService.findById(accountId, context.getUser.getAccountId)
        if (account != null) {
          val accountIconLink: String = CrmResources.getResourceLink(CrmTypeConstants.ACCOUNT)
          val img: Img = TagBuilder.newImg("avatar", accountIconLink)
          val accountLink: String = CrmLinkGenerator.generateAccountPreviewFullLink(context.siteUrl, account.getId)
          val link: A = TagBuilder.newA(accountLink, account.getAccountname)
           TagBuilder.newLink(img, link).write
        }
      }
      catch {
        case e: Exception => LOG.error("Error", e)
      }
       value
    }
  }

  class AssigneeFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val simpleCase: SimpleCase = context.getWrappedBean.asInstanceOf[SimpleCase]
      if (simpleCase.getAssignuser != null) {
        val userAvatarLink: String = MailUtils.getAvatarLink(simpleCase.getAssignUserAvatarId, 16)
        val img: Img = TagBuilder.newImg("avatar", userAvatarLink)
        val userLink: String = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(simpleCase.getSaccountid), simpleCase.getAssignuser)
        val link: A = TagBuilder.newA(userLink, simpleCase.getAssignUserFullName)
         TagBuilder.newLink(img, link).write
      }
      else {
         new Span().write
      }
    }

    def formatField(context: MailContext[_], value: String): String = {
      if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
         new Span().write
      }
      val userService: UserService = ApplicationContextUtil.getSpringBean(classOf[UserService])
      val user: SimpleUser = userService.findUserByUserNameInAccount(value, context.getUser.getAccountId)
      if (user != null) {
        val userAvatarLink: String = MailUtils.getAvatarLink(user.getAvatarid, 16)
        val userLink: String = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(user.getAccountId), user.getUsername)
        val img: Img = TagBuilder.newImg("avatar", userAvatarLink)
        val link: A = TagBuilder.newA(userLink, user.getDisplayName)
         TagBuilder.newLink(img, link).write
      }
       value
    }
  }
}
