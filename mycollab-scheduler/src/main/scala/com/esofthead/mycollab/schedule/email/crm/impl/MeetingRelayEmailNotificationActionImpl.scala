package com.esofthead.mycollab.schedule.email.crm.impl

import com.esofthead.mycollab.common.MonitorTypeConstants
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification
import com.esofthead.mycollab.common.i18n.GenericI18Enum
import com.esofthead.mycollab.configuration.StorageManager
import com.esofthead.mycollab.core.utils.StringUtils
import com.esofthead.mycollab.module.crm.CrmLinkGenerator
import com.esofthead.mycollab.module.crm.domain.{MeetingWithBLOBs, SimpleMeeting}
import com.esofthead.mycollab.module.crm.i18n.MeetingI18nEnum
import com.esofthead.mycollab.module.crm.service.MeetingService
import com.esofthead.mycollab.module.user.domain.SimpleUser
import com.esofthead.mycollab.schedule.email.crm.MeetingRelayEmailNotificationAction
import com.esofthead.mycollab.schedule.email.format.DateTimeFieldFormat
import com.esofthead.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import com.hp.gagawa.java.elements.Img
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
class MeetingRelayEmailNotificationActionImpl extends CrmDefaultSendingRelayEmailAction[SimpleMeeting] with MeetingRelayEmailNotificationAction {

  @Autowired var meetingService: MeetingService = _

  private val mapper = new MeetingFieldNameMapper

  protected def buildExtraTemplateVariables(context: MailContext[SimpleMeeting]) {
    val summary: String = bean.getSubject
    val summaryLink: String = CrmLinkGenerator.generateMeetingPreviewFullLink(siteUrl, bean.getId)
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
      contentGenerator.putVariable("actionHeading", context.getMessage(MeetingI18nEnum.MAIL_CREATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.UPDATE_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(MeetingI18nEnum.MAIL_UPDATE_ITEM_HEADING, makeChangeUser))
    }
    else if (MonitorTypeConstants.ADD_COMMENT_ACTION == emailNotification.getAction) {
      contentGenerator.putVariable("actionHeading", context.getMessage(MeetingI18nEnum.MAIL_COMMENT_ITEM_HEADING, makeChangeUser))
    }
    contentGenerator.putVariable("summary", summary)
    contentGenerator.putVariable("summaryLink", summaryLink)
  }

  protected def getCreateSubjectKey: Enum[_] = MeetingI18nEnum.MAIL_CREATE_ITEM_SUBJECT

  protected def getUpdateSubjectKey: Enum[_] = MeetingI18nEnum.MAIL_UPDATE_ITEM_SUBJECT

  protected def getCommentSubjectKey: Enum[_] = MeetingI18nEnum.MAIL_COMMENT_ITEM_SUBJECT

  protected def getItemName: String = StringUtils.trim(bean.getSubject, 100)

  protected def getItemFieldMapper: ItemFieldMapper = mapper

  protected def getBeanInContext(context: MailContext[SimpleMeeting]): SimpleMeeting = meetingService.findById(context.getTypeid.toInt, context.getSaccountid)

  class MeetingFieldNameMapper extends ItemFieldMapper {
    put(MeetingWithBLOBs.Field.subject, MeetingI18nEnum.FORM_SUBJECT, true)
    put(MeetingWithBLOBs.Field.status, MeetingI18nEnum.FORM_STATUS)
    put(MeetingWithBLOBs.Field.startdate, new DateTimeFieldFormat(MeetingWithBLOBs.Field.startdate.name, MeetingI18nEnum.FORM_START_DATE_TIME))
    put(MeetingWithBLOBs.Field.location, MeetingI18nEnum.FORM_LOCATION)
    put(MeetingWithBLOBs.Field.enddate, new DateTimeFieldFormat(MeetingWithBLOBs.Field.enddate.name, MeetingI18nEnum.FORM_END_DATE_TIME))
    put(MeetingWithBLOBs.Field.description, GenericI18Enum.FORM_DESCRIPTION, true)

  }

}
