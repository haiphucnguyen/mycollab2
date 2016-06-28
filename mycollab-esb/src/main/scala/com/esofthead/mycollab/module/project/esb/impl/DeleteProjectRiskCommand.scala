package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.common.dao.CommentMapper
import com.esofthead.mycollab.common.domain.{CommentExample, TagExample}
import com.esofthead.mycollab.common.service.TagService
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.file.AttachmentUtils
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.esb.DeleteProjectRiskEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired

/**
  * @author MyCollab Ltd
  * @since 5.2.9
  */
class DeleteProjectRiskCommand extends GenericCommand {
  @Autowired private val resourceService: ResourceService = null
  @Autowired private val commentMapper: CommentMapper = null
  @Autowired private val tagService: TagService = null

  @AllowConcurrentEvents
  @Subscribe
  def removeRisk(event: DeleteProjectRiskEvent): Unit = {
    for (risk <- event.risks) {
      removeRelatedFiles(event.accountId, risk.getProjectid, risk.getId)
      removeRelatedComments(risk.getId)
      removeRelatedTags(risk.getId)
    }
  }

  private def removeRelatedFiles(accountId: Integer, projectId: Integer, riskId: Integer) {
    val attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
      ProjectTypeConstants.RISK, "" + riskId)
    resourceService.removeResource(attachmentPath, "", accountId)
  }

  private def removeRelatedComments(riskId: Integer) {
    val ex = new CommentExample
    ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.RISK).andExtratypeidEqualTo(riskId)
    commentMapper.deleteByExample(ex)
  }

  private def removeRelatedTags(riskId: Integer): Unit = {
    val ex = new TagExample
    ex.createCriteria().andTypeEqualTo(ProjectTypeConstants.RISK).andTypeidEqualTo(riskId + "")
    tagService.deleteByExample(ex)
  }
}
