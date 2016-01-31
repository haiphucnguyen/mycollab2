package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.common.ModuleNameConstants
import com.esofthead.mycollab.common.dao.{ActivityStreamMapper, CommentMapper, OptionValMapper}
import com.esofthead.mycollab.common.domain.{ActivityStreamExample, CommentExample, OptionValExample}
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.page.service.PageService
import com.esofthead.mycollab.module.project.esb.DeleteProjectEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
@Component class DeleteProjectCommandImpl extends GenericCommand {
  @Autowired private val activityStreamMapper: ActivityStreamMapper = null
  @Autowired private val commentMapper: CommentMapper = null
  @Autowired private val resourceService: ResourceService = null
  @Autowired private val pageService: PageService = null
  @Autowired private val optionValMapper: OptionValMapper = null

  @AllowConcurrentEvents
  @Subscribe
  def removedProject(event: DeleteProjectEvent): Unit = {
    for (project <- event.projects) {
      deleteProjectActivityStream(project.getId)
      deleteRelatedComments(project.getId)
      deleteProjectFiles(event.accountId, project.getId)
      deleteProjectPages(event.accountId, project.getId)
      deleteProjectOptions(event.accountId, project.getId);
    }
  }

  private def deleteProjectActivityStream(projectId: Integer) {
    val ex: ActivityStreamExample = new ActivityStreamExample
    ex.createCriteria.andExtratypeidEqualTo(projectId).andModuleEqualTo(ModuleNameConstants.PRJ)
    activityStreamMapper.deleteByExample(ex)
  }

  private def deleteRelatedComments(projectId: Integer) {
    val ex: CommentExample = new CommentExample
    ex.createCriteria.andExtratypeidEqualTo(projectId)
    commentMapper.deleteByExample(ex)
  }

  private def deleteProjectFiles(accountid: Integer, projectId: Integer) {
    val rootPath: String = String.format("%d/project/%d", accountid, projectId)
    resourceService.removeResource(rootPath, "", accountid)
  }

  private def deleteProjectPages(accountid: Integer, projectId: Integer) {
    val rootPath: String = String.format("%d/project/%d/.page", accountid, projectId)
    pageService.removeResource(rootPath)
  }

  private def deleteProjectOptions(accountid: Integer, projectId: Integer): Unit = {
    val ex: OptionValExample = new OptionValExample
    ex.createCriteria().andExtraidEqualTo(projectId)
    optionValMapper.deleteByExample(ex)
  }
}