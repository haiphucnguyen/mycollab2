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
    val attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
      ProjectTypeConstants.BUG_VERSION, "" + versionId)
    resourceService.removeResource(attachmentPath, "", true, accountId)
  }

  private def removeRelatedComments(bugId: Integer) {
    val ex = new CommentExample
    ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.BUG_VERSION).andExtratypeidEqualTo(bugId)
    commentMapper.deleteByExample(ex)
  }
}