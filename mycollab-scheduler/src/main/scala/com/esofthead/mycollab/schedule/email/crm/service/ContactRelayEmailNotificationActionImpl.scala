package com.esofthead.mycollab.schedule.email.crm.service

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.html.{FormatUtils, LinkUtils}
import com.esofthead.mycollab.module.crm.domain.{Contact, SimpleContact}
import com.esofthead.mycollab.module.crm.i18n.ContactI18nEnum
import com.esofthead.mycollab.module.crm.service.{AccountService, ContactService}
import com.esofthead.mycollab.module.crm.{CrmLinkGenerator, CrmResources, CrmTypeConstants}
import com.esofthead.mycollab.module.mail.MailUtils
import com.esofthead.mycollab.module.user.AccountLinkGenerator
import com.esofthead.mycollab.module.user.service.UserService
import com.esofthead.mycollab.schedule.email.crm.ContactRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.format.{DateFieldFormat, EmailLinkFieldFormat, FieldFormat}
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import com.esofthead.mycollab.spring.AppContextUtil
import com.hp.gagawa.java.elements.{Span, Text}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 4.6.0
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ContactRelayEmailNotificationActionImpl extends CrmDefaultSendingRelayEmailAction[SimpleContact] with ContactRelayEmailNotificationAction {
  private val LOG = LoggerFactory.getLogger(classOf[ContactRelayEmailNotificationActionImpl])

  @Autowired var contactService: ContactService = _

  private val mapper = new ContactFieldNameMapper

  override protected def getBeanInContext(notification: SimpleRelayEmailNotification): SimpleContact =
    contactService.findById(notification.getTypeid.toInt, notification.getSaccountid)

  override protected def getCreateSubjectKey: Enum[_] = ContactI18nEnum.MAIL_CREATE_ITEM_SUBJECT

  override protected def getCommentSubjectKey: Enum[_] = ContactI18nEnum.MAIL_COMMENT_ITEM_SUBJECT

  override protected def getItemFieldMapper: ItemFieldMapper = mapper

  override protected def getItemName: String = StringUtils.trim(bean.getContactName, 100)

  override protected def buildExtraTemplateVariables(context: MailContext[SimpleContact]): Unit = {
    val summary = bean.getContactName
    val summaryLink = CrmLinkGenerator.generateContactPreviewFullLink(siteUrl, bean.getId)

    val emailNotification = context.getEmailNotification

    val avatarId = if (changeUser != null) changeUser.getAvatarid else ""
    val userAvatar = LinkUtils.newAvatar(avatarId)

    val makeChangeUser = userAvatar.toString + emailNotification.getChangeByUserFullName
    val actionEnum = emailNotification.getAction match {
      case MonitorTypeConstants.CREATE_ACTION => ContactI18nEnum.MAIL_CREATE_ITEM_HEADING
      case MonitorTypeConstants.UPDATE_ACTION => ContactI18nEnum.MAIL_UPDATE_ITEM_HEADING
      case MonitorTypeConstants.ADD_COMMENT_ACTION => ContactI18nEnum.MAIL_COMMENT_ITEM_HEADING
    }

    contentGenerator.putVariable("actionHeading", context.getMessage(actionEnum, makeChangeUser))
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
  }

  override protected def getUpdateSubjectKey: Enum[_] = ContactI18nEnum.MAIL_UPDATE_ITEM_SUBJECT

  class ContactFieldNameMapper extends ItemFieldMapper {
    put(Contact.Field.firstname, ContactI18nEnum.FORM_FIRSTNAME)
    put(Contact.Field.officephone, ContactI18nEnum.FORM_OFFICE_PHONE)
    put(Contact.Field.lastname, ContactI18nEnum.FORM_LASTNAME)
    put(Contact.Field.mobile, ContactI18nEnum.FORM_MOBILE)
    put(Contact.Field.accountid, new AccountFieldFormat(Contact.Field.accountid.name, ContactI18nEnum.FORM_ACCOUNTS))
    put(Contact.Field.homephone, ContactI18nEnum.FORM_HOME_PHONE)
    put(Contact.Field.title, ContactI18nEnum.FORM_TITLE)
    put(Contact.Field.otherphone, ContactI18nEnum.FORM_OTHER_PHONE)
    put(Contact.Field.department, ContactI18nEnum.FORM_DEPARTMENT)
    put(Contact.Field.fax, ContactI18nEnum.FORM_FAX)
    put(Contact.Field.email, new EmailLinkFieldFormat(Contact.Field.email.name, ContactI18nEnum.FORM_EMAIL))
    put(Contact.Field.birthday, new DateFieldFormat(Contact.Field.birthday.name, ContactI18nEnum.FORM_BIRTHDAY))
    put(Contact.Field.assistant, ContactI18nEnum.FORM_ASSISTANT)
    put(Contact.Field.iscallable, ContactI18nEnum.FORM_IS_CALLABLE)
    put(Contact.Field.assistantphone, ContactI18nEnum.FORM_ASSISTANT_PHONE)
    put(Contact.Field.assignuser, new AssigneeFieldFormat(Contact.Field.assignuser.name, GenericI18Enum.FORM_ASSIGNEE))
    put(Contact.Field.leadsource, ContactI18nEnum.FORM_LEAD_SOURCE, isColSpan = true)
    put(Contact.Field.primaddress, ContactI18nEnum.FORM_PRIMARY_ADDRESS)
    put(Contact.Field.otheraddress, ContactI18nEnum.FORM_OTHER_ADDRESS)
    put(Contact.Field.primcity, ContactI18nEnum.FORM_PRIMARY_CITY)
    put(Contact.Field.othercity, ContactI18nEnum.FORM_OTHER_CITY)
    put(Contact.Field.primstate, ContactI18nEnum.FORM_PRIMARY_STATE)
    put(Contact.Field.otherstate, ContactI18nEnum.FORM_OTHER_STATE)
    put(Contact.Field.primpostalcode, ContactI18nEnum.FORM_PRIMARY_POSTAL_CODE)
    put(Contact.Field.otherpostalcode, ContactI18nEnum.FORM_OTHER_POSTAL_CODE)
    put(Contact.Field.primcountry, ContactI18nEnum.FORM_PRIMARY_COUNTRY)
    put(Contact.Field.othercountry, ContactI18nEnum.FORM_OTHER_COUNTRY)
    put(Contact.Field.description, GenericI18Enum.FORM_DESCRIPTION, isColSpan = true)
  }

  class AssigneeFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val contact = context.getWrappedBean.asInstanceOf[SimpleContact]
      if (contact.getAssignuser != null) {
        val userAvatarLink = MailUtils.getAvatarLink(contact.getAssignUserAvatarId, 16)
        val img = FormatUtils.newImg("avatar", userAvatarLink)
        val userLink = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(
          contact.getSaccountid), contact.getAssignuser)
        val link = FormatUtils.newA(userLink, contact.getAssignUserFullName)
        FormatUtils.newLink(img, link).write
      }
      else {
        new Span().write
      }
    }

    def formatField(context: MailContext[_], value: String): String = {
      if (StringUtils.isBlank(value)) {
        new Span().write
      } else {
        val userService = AppContextUtil.getSpringBean(classOf[UserService])
        val user = userService.findUserByUserNameInAccount(value, context.getUser.getAccountId)
        if (user != null) {
          val userAvatarLink = MailUtils.getAvatarLink(user.getAvatarid, 16)
          val userLink = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(
            user.getAccountId), user.getUsername)
          val img = FormatUtils.newImg("avatar", userAvatarLink)
          val link = FormatUtils.newA(userLink, user.getDisplayName)
          FormatUtils.newLink(img, link).write
        } else
          value
      }
    }
  }

  class AccountFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val contact = context.getWrappedBean.asInstanceOf[SimpleContact]
      if (contact.getAccountid != null) {
        val img = new Text(CrmResources.getFontIconHtml(CrmTypeConstants.ACCOUNT))
        val accountLink = CrmLinkGenerator.generateAccountPreviewFullLink(context.siteUrl, contact.getAccountid)
        val link = FormatUtils.newA(accountLink, contact.getAccountName)
        FormatUtils.newLink(img, link).write
      }
      else {
        new Span().write
      }
    }

    def formatField(context: MailContext[_], value: String): String = {
      if (StringUtils.isBlank(value)) {
        new Span().write
      }
      try {
        val accountId = value.toInt
        val accountService = AppContextUtil.getSpringBean(classOf[AccountService])
        val account = accountService.findById(accountId, context.getUser.getAccountId)
        if (account != null) {
          val img = new Text(CrmResources.getFontIconHtml(CrmTypeConstants.ACCOUNT))
          val accountLink = CrmLinkGenerator.generateAccountPreviewFullLink(context.siteUrl, account.getId)
          val link = FormatUtils.newA(accountLink, account.getAccountname)
          return FormatUtils.newLink(img, link).write
        }
      }
      catch {
        case e: Exception => LOG.error("Error", e)
      }
      value
    }
  }

}
