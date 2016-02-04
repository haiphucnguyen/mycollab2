package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.2.7
  */
object AssignmentEvent {

  class NewAssignmentAdd(source: AnyRef, typeVal: String, typeIdVal: Integer) extends ApplicationEvent(source, null) {}

}
