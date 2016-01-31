package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.common.dao.CommentMapper
import com.esofthead.mycollab.common.domain.CommentExample
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.file.AttachmentUtils
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.dao.PredecessorMapper
import com.esofthead.mycollab.module.project.domain.PredecessorExample
import com.esofthead.mycollab.module.project.esb.DeleteProjectTaskEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
@Component class DeleteProjectTaskCommand extends GenericCommand {
  @Autowired private val resourceService: ResourceService = null
  @Autowired private val commentMapper: CommentMapper = null
  @Autowired private val predecessorMapper: PredecessorMapper = null

  @AllowConcurrentEvents
  @Subscribe
  def removedTask(event: DeleteProjectTaskEvent): Unit = {
    for (task <- event.tasks) {
      removeRelatedFiles(event.accountId, task.getProjectid, task.getId)
      removeRelatedComments(task.getId)
      removePredecessorTasks(task.getId)
    }

  }

  private def removeRelatedFiles(accountId: Integer, projectId: Integer, taskId: Integer) {
    val attachmentPath: String = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
      ProjectTypeConstants.TASK, "" + taskId)
    resourceService.removeResource(attachmentPath, "", accountId)
  }

  private def removeRelatedComments(taskId: Integer) {
    val ex: CommentExample = new CommentExample
    ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.TASK).andExtratypeidEqualTo(taskId)
    commentMapper.deleteByExample(ex)
  }

  private def removePredecessorTasks(taskId: Integer): Unit = {
    val ex: PredecessorExample = new PredecessorExample
    ex.or().andSourceidEqualTo(taskId).andSourcetypeEqualTo(ProjectTypeConstants.TASK)
    ex.or().andDescidEqualTo(taskId).andDesctypeEqualTo(ProjectTypeConstants.TASK)
    predecessorMapper.deleteByExample(ex);
  }
}