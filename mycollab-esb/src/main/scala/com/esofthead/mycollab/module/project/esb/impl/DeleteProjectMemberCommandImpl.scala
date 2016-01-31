package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.project.esb.DeleteProjectMemberEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
@Component class DeleteProjectMemberCommandImpl extends GenericCommand {
  @AllowConcurrentEvents
  @Subscribe
  def deleteProjectMember(event: DeleteProjectMemberEvent): Unit = {

  }
}