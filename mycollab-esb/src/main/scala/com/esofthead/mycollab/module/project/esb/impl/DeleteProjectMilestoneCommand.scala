package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.common.dao.CommentMapper
import com.esofthead.mycollab.common.domain.{CommentExample, TagExample}
import com.esofthead.mycollab.common.service.TagService
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.file.AttachmentUtils
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.dao.PredecessorMapper
import com.esofthead.mycollab.module.project.domain.PredecessorExample
import com.esofthead.mycollab.module.project.esb.DeleteProjectMilestoneEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
@Component class DeleteProjectMilestoneCommand extends GenericCommand {
  @Autowired private val resourceService: ResourceService = null
  @Autowired private val commentMapper: CommentMapper = null
  @Autowired private val predecessorMapper: PredecessorMapper = null
  @Autowired private val tagService: TagService = null

  @AllowConcurrentEvents
  @Subscribe
  def removedMilestone(event: DeleteProjectMilestoneEvent): Unit = {
    removeRelatedFiles(event.accountId, event.projectId, event.milestoneId)
    removeRelatedComments(event.milestoneId)
    removePredecessorMilestones(event.milestoneId)
    removeRelatedTags(event.milestoneId)
  }

  private def removeRelatedFiles(accountId: Integer, projectId: Integer, milestoneId: Integer) {
    val attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
      ProjectTypeConstants.MILESTONE, "" + milestoneId)
    resourceService.removeResource(attachmentPath, "", accountId)
  }

  private def removeRelatedComments(milestoneId: Integer) {
    val ex = new CommentExample
    ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.MILESTONE).andExtratypeidEqualTo(milestoneId)
    commentMapper.deleteByExample(ex)
  }

  private def removePredecessorMilestones(milestoneId: Integer): Unit = {
    val ex = new PredecessorExample
    ex.or().andSourceidEqualTo(milestoneId).andSourcetypeEqualTo(ProjectTypeConstants.MILESTONE)
    ex.or().andDescidEqualTo(milestoneId).andDesctypeEqualTo(ProjectTypeConstants.MILESTONE)
    predecessorMapper.deleteByExample(ex);
  }

  private def removeRelatedTags(milestoneId: Integer): Unit = {
    val ex = new TagExample
    ex.createCriteria().andTypeEqualTo(ProjectTypeConstants.MILESTONE).andTypeidEqualTo(milestoneId + "")
    tagService.deleteByExample(ex)
  }
}