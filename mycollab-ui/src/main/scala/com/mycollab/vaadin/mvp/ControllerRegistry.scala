package com.mycollab.vaadin.mvp

import com.mycollab.vaadin.ui.MyCollabSession
import com.mycollab.vaadin.ui.MyCollabSession._

import scala.collection.mutable._

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object ControllerRegistry {
  def addController(controller: AbstractController): Unit = {
    var controllerList = (MyCollabSession.getCurrentUIVariable(CONTROLLER_REGISTRY).
      asInstanceOf[Map[Class[_], AbstractController]])
    if (controllerList == null) {
      controllerList = Map().withDefaultValue(null)
      MyCollabSession.putCurrentUIVariable(CONTROLLER_REGISTRY, controllerList)
    }
    val existingController = controllerList(controller.getClass)
    if (existingController != null) {
      existingController.unregisterAll
    }
    controllerList += (controller.getClass -> controller)
  }
}
