package com.mycollab.module.project.esb

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.common.dao.MonitorItemMapper
import com.mycollab.common.domain.MonitorItemExample
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.project.domain.ProjectMember
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
@Component class DeleteProjectMemberCommandImpl extends GenericCommand {
  @Autowired private val monitorItemMapper: MonitorItemMapper = null

  @AllowConcurrentEvents
  @Subscribe
  def deleteProjectMember(event: DeleteProjectMemberEvent): Unit = {
    removeAssociateWatchers(event.members)
  }

  private def removeAssociateWatchers(members: Array[ProjectMember]): Unit = {
    for (member <- members) {
      val monitorEx = new MonitorItemExample
      monitorEx.createCriteria().andExtratypeidEqualTo(member.getProjectid).andUserEqualTo(member.getUsername)
        .andSaccountidEqualTo(member.getSaccountid)
      monitorItemMapper.deleteByExample(monitorEx)
    }
  }
}