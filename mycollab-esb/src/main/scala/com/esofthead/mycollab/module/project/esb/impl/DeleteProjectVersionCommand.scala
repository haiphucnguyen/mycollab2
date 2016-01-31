package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.common.dao.CommentMapper
import com.esofthead.mycollab.common.domain.CommentExample
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.file.AttachmentUtils
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.esb.DeleteProjectVersionEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
@Component class DeleteProjectVersionCommand extends GenericCommand {
  @Autowired private val resourceService: ResourceService = null
  @Autowired private val commentMapper: CommentMapper = null

  @AllowConcurrentEvents
  @Subscribe
  def removedVersion(event: DeleteProjectVersionEvent): Unit = {
    removeRelatedFiles(event.accountId, event.projectId, event.versionId)
    removeRelatedComments(event.versionId)
  }

  private def removeRelatedFiles(accountId: Integer, projectId: Integer, versionId: Integer) {
    val attachmentPath: String = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
      ProjectTypeConstants.BUG_VERSION, "" + versionId)
    resourceService.removeResource(attachmentPath, "", accountId)
  }

  private def removeRelatedComments(bugId: Integer) {
    val ex: CommentExample = new CommentExample
    ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.BUG_VERSION).andExtratypeidEqualTo(bugId)
    commentMapper.deleteByExample(ex)
  }
}