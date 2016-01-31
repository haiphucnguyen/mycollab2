package com.esofthead.mycollab.module

import javax.annotation.{PostConstruct, PreDestroy}

import com.google.common.eventbus.AsyncEventBus
import org.springframework.beans.factory.annotation.Autowired

/**
  * @author MyCollab Ltd
  * @since 5.1.0
  */
class GenericCommand {
  @Autowired protected val asyncEventBus: AsyncEventBus = null

  @PostConstruct
  def registerHandler(): Unit = {
    asyncEventBus.register(this)
  }

  @PreDestroy
  def unregisterHandler(): Unit = {
    asyncEventBus.unregister(this)
  }
}
