package com.esofthead.mycollab.module.user.esb.impl

import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.mail.service.{IContentGenerator, ExtMailService}
import com.esofthead.mycollab.module.user.esb.NewUserJoinEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired

/**
  * @author MyCollab Ltd
  * @since 5.2.6
  */
object NewUserJoinCommand {

}

class NewUserJoinCommand extends GenericCommand {

  @Autowired private val extMailService: ExtMailService = null
  @Autowired private val contentGenerator: IContentGenerator = null

  @AllowConcurrentEvents
  @Subscribe
  def execute(event: NewUserJoinEvent): Unit = {

  }
}
