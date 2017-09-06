package com.mycollab.module.project.schedule.email.service

import com.mycollab.module.page.domain.Page
import com.mycollab.module.page.service.PageService
import com.mycollab.module.project.domain.ProjectRelayEmailNotification
import com.mycollab.schedule.email.project.ProjectPageRelayEmailNotificationAction
import com.mycollab.core.utils.StringUtils
import com.mycollab.schedule.email.{ItemFieldMapper, MailContext}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ProjectPageRelayEmailNotificationActionImpl extends SendMailToAllMembersAction[Page] with ProjectPageRelayEmailNotificationAction {
  @Autowired private val pageService: PageService = null

  protected def getBeanInContext(notification: ProjectRelayEmailNotification): Page =
    pageService.getPage(notification.getTypeid, "")

  protected def buildExtraTemplateVariables(context: MailContext[Page]) {}

  protected def getItemName: String = StringUtils.trim(bean.getSubject, 100)
  
  override protected def getProjectName: String = ""
  
  protected def getCreateSubject(context: MailContext[Page]): String = null

  protected def getUpdateSubject(context: MailContext[Page]): String = null

  protected def getCommentSubject(context: MailContext[Page]): String = null

  protected def getItemFieldMapper: ItemFieldMapper = null
}
