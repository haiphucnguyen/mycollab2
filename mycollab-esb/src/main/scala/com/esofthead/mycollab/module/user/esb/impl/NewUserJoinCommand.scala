package com.esofthead.mycollab.module.user.esb.impl

import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.user.esb.NewUserJoinEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}

/**
  * @author MyCollab Ltd
  * @since 5.2.6
  */
class NewUserJoinCommand extends GenericCommand {

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: NewUserJoinEvent): Unit = {

  }
}
