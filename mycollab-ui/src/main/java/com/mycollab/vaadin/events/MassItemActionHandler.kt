package com.mycollab.vaadin.events

import com.mycollab.reporting.ReportExportType
import com.vaadin.server.StreamResource

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
interface MassItemActionHandler {
    fun onSelect(id: String)

    fun buildStreamResource(exportType: ReportExportType): StreamResource
}

object ViewItemAction {
    @JvmField val MAIL_ACTION = "mail"
    @JvmField val DELETE_ACTION = "delete"
    @JvmField val MASS_UPDATE_ACTION = "massUpdate"
}