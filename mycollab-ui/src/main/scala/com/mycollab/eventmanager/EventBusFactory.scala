package com.mycollab.eventmanager

import com.google.common.eventbus.{EventBus, SubscriberExceptionContext, SubscriberExceptionHandler}
import com.mycollab.vaadin.ui.MyCollabSession
import com.mycollab.vaadin.ui.MyCollabSession._
import org.slf4j.LoggerFactory

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */

object EventBusFactory {
  
  private val LOG = LoggerFactory.getLogger(EventBusFactory.getClass)
  
  def getInstance(): EventBus = {
    var eventBus = MyCollabSession.getCurrentUIVariable(EVENT_BUS_VAL).asInstanceOf[EventBus]
    if (eventBus == null) {
      eventBus = new EventBus(new SubscriberEventBusExceptionHandler)
      MyCollabSession.putCurrentUIVariable(EVENT_BUS_VAL, eventBus)
    }
    eventBus
  }
  
  private class SubscriberEventBusExceptionHandler extends SubscriberExceptionHandler {
    def handleException(e: Throwable, context: SubscriberExceptionContext) {
      LOG.error("Error", e)
    }
  }
  
}
