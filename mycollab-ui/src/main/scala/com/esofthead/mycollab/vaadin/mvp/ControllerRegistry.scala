package com.esofthead.mycollab.vaadin.mvp

import com.esofthead.mycollab.vaadin.ui.MyCollabSession
import com.esofthead.mycollab.vaadin.ui.MyCollabSession._

import scala.collection.mutable._

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object ControllerRegistry {
    def addController(controller: AbstractController): Unit = {
        var controllerList: Map[Class[_], AbstractController] = (MyCollabSession.getVariable(CONTROLLER_REGISTRY).asInstanceOf[Map[Class[_], AbstractController]])
        if (controllerList == null) {
            controllerList = Map().withDefaultValue(null)
            MyCollabSession.putVariable(CONTROLLER_REGISTRY, controllerList)
        }
        val existingController: AbstractController = controllerList(controller.getClass)
        if (existingController != null) {
            existingController.unregisterAll
        }
        controllerList += (controller.getClass -> controller)
    }
}
