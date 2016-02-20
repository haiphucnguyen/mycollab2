package com.esofthead.mycollab.eventmanager

import com.esofthead.mycollab.core.MyCollabException
import com.google.common.eventbus.EventBus

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
abstract class EventBusFactory {
  protected def getInstanceInSession: EventBus
}

object EventBusFactory {
  private val eventBusFactoryImplClsName: String = "com.esofthead.mycollab.eventmanager.EventBusFactoryImpl"
  private var eventBusFactoryImpl: EventBusFactory = null

  try {
    val cls: Class[EventBusFactory] = Class.forName(eventBusFactoryImplClsName).asInstanceOf[Class[EventBusFactory]]
    eventBusFactoryImpl = cls.newInstance
  }
  catch {
    case e: Exception => throw new MyCollabException(e)
  }

  def getInstance(): EventBus = eventBusFactoryImpl.getInstanceInSession
}
