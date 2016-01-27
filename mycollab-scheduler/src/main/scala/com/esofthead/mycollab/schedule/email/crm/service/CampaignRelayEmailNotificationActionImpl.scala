package com.esofthead.mycollab.schedule.email.crm.service

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.html.{FormatUtils, LinkUtils}
import com.esofthead.mycollab.module.crm.CrmLinkGenerator
import com.esofthead.mycollab.module.crm.domain.{CampaignWithBLOBs, SimpleCampaign}
import com.esofthead.mycollab.module.crm.i18n.CampaignI18nEnum
import com.esofthead.mycollab.module.crm.service.CampaignService
import com.esofthead.mycollab.module.mail.MailUtils
import com.esofthead.mycollab.module.user.AccountLinkGenerator
import com.esofthead.mycollab.module.user.service.UserService
import com.esofthead.mycollab.schedule.email.crm.CampaignRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.format.{CurrencyFieldFormat, DateFieldFormat, FieldFormat}
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import com.esofthead.mycollab.spring.ApplicationContextUtil
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
class CampaignRelayEmailNotificationActionImpl extends CrmDefaultSendingRelayEmailAction[SimpleCampaign] with CampaignRelayEmailNotificationAction {
  @Autowired var campaignService: CampaignService = _
  private val mapper: CampaignFieldNameMapper = new CampaignFieldNameMapper

  override protected def getBeanInContext(context: MailContext[SimpleCampaign]): SimpleCampaign = campaignService.findById(
    context.getTypeid.toInt, context.getSaccountid)

  override protected def getCreateSubjectKey: Enum[_] = CampaignI18nEnum.MAIL_CREATE_ITEM_SUBJECT

  override protected def getCommentSubjectKey: Enum[_] = CampaignI18nEnum.MAIL_COMMENT_ITEM_SUBJECT

  override protected def getItemFieldMapper: ItemFieldMapper = mapper

  override protected def getItemName: String = StringUtils.trim(bean.getCampaignname, 100)

  override protected def buildExtraTemplateVariables(context: MailContext[SimpleCampaign]): Unit = {
    val summary = bean.getCampaignname
    val summaryLink = CrmLinkGenerator.generateCampaignPreviewFullLink(siteUrl, bean.getId)

    val emailNotification = context.getEmailNotification
    val user = userService.findUserByUserNameInAccount(emailNotification.getChangeby, context.getSaccountid)

    val avatarId = if (user != null) user.getAvatarid else ""
    val userAvatar = LinkUtils.newAvatar(avatarId)

    val makeChangeUser = userAvatar.toString + emailNotification.getChangeByUserFullName
    val actionEnum = emailNotification.getAction match {
      case MonitorTypeConstants.CREATE_ACTION => CampaignI18nEnum.MAIL_CREATE_ITEM_HEADING
      case MonitorTypeConstants.UPDATE_ACTION => CampaignI18nEnum.MAIL_UPDATE_ITEM_HEADING
      case MonitorTypeConstants.ADD_COMMENT_ACTION => CampaignI18nEnum.MAIL_COMMENT_ITEM_HEADING
    }

    contentGenerator.putVariable("actionHeading", context.getMessage(actionEnum, makeChangeUser))
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
  }

  override protected def getUpdateSubjectKey: Enum[_] = CampaignI18nEnum.MAIL_UPDATE_ITEM_SUBJECT

  class CampaignFieldNameMapper extends ItemFieldMapper {
    put(CampaignWithBLOBs.Field.campaignname, CampaignI18nEnum.FORM_CAMPAIGN_NAME, isColSpan = true)
    put(CampaignWithBLOBs.Field.status, CampaignI18nEnum.FORM_STATUS)
    put(CampaignWithBLOBs.Field.`type`, CampaignI18nEnum.FORM_TYPE)
    put(CampaignWithBLOBs.Field.currencyid, new CurrencyFieldFormat(CampaignWithBLOBs.Field.currencyid.name, CampaignI18nEnum.FORM_CURRENCY))
    put(CampaignWithBLOBs.Field.budget, CampaignI18nEnum.FORM_BUDGET)
    put(CampaignWithBLOBs.Field.expectedcost, CampaignI18nEnum.FORM_EXPECTED_COST)
    put(CampaignWithBLOBs.Field.expectedrevenue, CampaignI18nEnum.FORM_EXPECTED_REVENUE)
    put(CampaignWithBLOBs.Field.actualcost, CampaignI18nEnum.FORM_ACTUAL_COST)
    put(CampaignWithBLOBs.Field.assignuser, new AssigneeFieldFormat(CampaignWithBLOBs.Field.assignuser.name, GenericI18Enum.FORM_ASSIGNEE))
    put(CampaignWithBLOBs.Field.startdate, new DateFieldFormat(CampaignWithBLOBs.Field.startdate.name, CampaignI18nEnum.FORM_START_DATE))
    put(CampaignWithBLOBs.Field.enddate, new DateFieldFormat(CampaignWithBLOBs.Field.enddate.name, CampaignI18nEnum.FORM_END_DATE))
    put(CampaignWithBLOBs.Field.description, GenericI18Enum.FORM_DESCRIPTION, isColSpan = true)
  }

  class AssigneeFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {

    def formatField(context: MailContext[_]): String = {
      val campaign = context.getWrappedBean.asInstanceOf[SimpleCampaign]
      if (campaign.getAssignuser != null) {
        val userAvatarLink = MailUtils.getAvatarLink(campaign.getAssignUserAvatarId, 16)
        val img = FormatUtils.newImg("avatar", userAvatarLink)
        val userLink = AccountLinkGenerator.generatePreviewFullUserLink(MailUtils.getSiteUrl(
          campaign.getSaccountid), campaign.getAssignuser)
        val link = FormatUtils.newA(userLink, campaign.getAssignUserFullName)
        FormatUtils.newLink(img, link).write
      }
      else {
        ""
      }
    }

    def formatField(context: MailContext[_], value: String): String = {
      if (StringUtils.isBlank(value)) {
        ""
      } else {
        val userService = ApplicationContextUtil.getSpringBean(classOf[UserService])
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

}
