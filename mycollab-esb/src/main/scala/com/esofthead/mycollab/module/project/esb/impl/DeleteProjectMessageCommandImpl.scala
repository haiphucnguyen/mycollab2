package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.common.dao.CommentMapper
import com.esofthead.mycollab.common.domain.CommentExample
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.file.AttachmentUtils
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.esb.DeleteProjectMessageEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

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
    val attachmentPath: String = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
      ProjectTypeConstants.MESSAGE, "" + messageId)
    resourceService.removeResource(attachmentPath, "", accountId)
  }

  private def removeRelatedComments(messageId: Integer) {
    val ex: CommentExample = new CommentExample
    ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.MESSAGE).andExtratypeidEqualTo(messageId)
    commentMapper.deleteByExample(ex)
  }
}