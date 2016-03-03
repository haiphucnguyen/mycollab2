package com.esofthead.mycollab.vaadin.events

import com.esofthead.mycollab.reporting.ReportExportType
import com.vaadin.server.StreamResource

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
trait MassItemActionHandler {
  def onSelect(id: String)

  def buildStreamResource(exportType: ReportExportType): StreamResource
}

object ViewItemAction {
  val MAIL_ACTION: String = "mail"
  val DELETE_ACTION: String = "delete"
  val MASS_UPDATE_ACTION: String = "massUpdate"
}


