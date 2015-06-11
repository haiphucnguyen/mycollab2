package com.esofthead.mycollab.vaadin.mvp

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.google.common.eventbus.EventBus

import scala.collection.mutable._

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
@SerialVersionUID(1L)
class AbstractController extends Serializable{
    private val eventBus:EventBus = EventBusFactory.getInstance()
    private val subscribers:Buffer[AnyRef] =  Buffer();

    def register(subscriber: AnyRef): Unit = {
        eventBus.register(subscribers)
        subscribers += subscriber
    }

    def unregisterAll {
        subscribers.foreach(subscriber => eventBus.unregister(subscriber))
    }
}
