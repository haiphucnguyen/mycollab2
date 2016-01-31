package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.common.dao.CommentMapper
import com.esofthead.mycollab.common.domain.CommentExample
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.file.AttachmentUtils
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.esb.DeleteProjectBugEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
@Component class DeleteProjectBugCommandImpl extends GenericCommand {
  @Autowired private val resourceService: ResourceService = null
  @Autowired private val commentMapper: CommentMapper = null

  @AllowConcurrentEvents
  @Subscribe
  def removeBugs(event: DeleteProjectBugEvent): Unit = {
    for (bug <- event.bugs) {
      removeRelatedFiles(event.accountId, bug.getProjectid, bug.getId)
      removeRelatedComments(bug.getId)
    }
  }

  private def removeRelatedFiles(accountId: Integer, projectId: Integer, bugId: Integer) {
    val attachmentPath: String = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
      ProjectTypeConstants.BUG, "" + bugId)
    resourceService.removeResource(attachmentPath, "", accountId)
  }

  private def removeRelatedComments(bugId: Integer) {
    val ex: CommentExample = new CommentExample
    ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.BUG).andExtratypeidEqualTo(bugId)
    commentMapper.deleteByExample(ex)
  }
}