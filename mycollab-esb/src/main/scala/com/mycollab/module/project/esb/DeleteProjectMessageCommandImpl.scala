package com.mycollab.module.project.esb

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.common.dao.CommentMapper
import com.mycollab.common.domain.CommentExample
import com.mycollab.module.ecm.service.ResourceService
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.file.AttachmentUtils
import com.mycollab.module.project.ProjectTypeConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 1.0.0
  */
@Component class DeleteProjectMessageCommandImpl extends GenericCommand {
  @Autowired private val resourceService: ResourceService = null
  @Autowired private val commentMapper: CommentMapper = null

  @AllowConcurrentEvents
  @Subscribe
  def removedMessage(event: DeleteProjectMessageEvent): Unit = {
    for (message <- event.messages) {
      removeRelatedFiles(event.accountId, message.getProjectid, message.getId)
      removeRelatedComments(message.getId)
    }
  }

  private def removeRelatedFiles(accountId: Integer, projectId: Integer, messageId: Integer) {
    val attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
      ProjectTypeConstants.MESSAGE, "" + messageId)
    resourceService.removeResource(attachmentPath, "", true, accountId)
  }

  private def removeRelatedComments(messageId: Integer) {
    val ex = new CommentExample
    ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.MESSAGE).andExtratypeidEqualTo(messageId)
    commentMapper.deleteByExample(ex)
  }
}