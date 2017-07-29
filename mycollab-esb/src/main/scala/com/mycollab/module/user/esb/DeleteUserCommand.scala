package com.mycollab.module.user.esb

import java.util.Arrays

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.cache.CleanCacheEvent
import com.mycollab.common.dao.MonitorItemMapper
import com.mycollab.common.domain.MonitorItemExample
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.project.ProjectMemberStatusConstants
import com.mycollab.module.project.dao.ProjectMemberMapper
import com.mycollab.module.project.domain.{ProjectMember, ProjectMemberExample}
import com.mycollab.module.project.service.ProjectMemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
@Component class DeleteUserCommand extends GenericCommand {
  @Autowired private val projectMemberMapper: ProjectMemberMapper = null
  @Autowired private val monitorItemMapper: MonitorItemMapper = null

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: DeleteUserEvent): Unit = {
    removeProjectInvolvement(event)
    removeUserMonitorItems(event)
    asyncEventBus.post(new CleanCacheEvent(event.accountid, Array(classOf[ProjectMemberService])))
  }

  private def removeProjectInvolvement(event: DeleteUserEvent): Unit = {
    val ex = new ProjectMemberExample
    ex.createCriteria.andStatusNotIn(Arrays.asList(ProjectMemberStatusConstants.INACTIVE)).
      andSaccountidEqualTo(event.accountid).andUsernameEqualTo(event.username)
    val projectMember = new ProjectMember
    projectMember.setStatus(ProjectMemberStatusConstants.INACTIVE)
    projectMemberMapper.updateByExampleSelective(projectMember, ex)
  }

  private def removeUserMonitorItems(event: DeleteUserEvent): Unit = {
    val ex = new MonitorItemExample
    ex.createCriteria().andSaccountidEqualTo(event.accountid).andUserEqualTo(event.username)
    monitorItemMapper.deleteByExample(ex)
  }
}