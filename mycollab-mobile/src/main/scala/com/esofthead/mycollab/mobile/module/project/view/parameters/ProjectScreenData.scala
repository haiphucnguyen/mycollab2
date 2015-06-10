package com.esofthead.mycollab.mobile.module.project.view.parameters

import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object ProjectScreenData {

    class Goto(param: Integer) extends ScreenData[Integer](param) {}

    class Edit(param: Integer) extends ScreenData[Integer](param) {}

    class ViewActivities() extends ScreenData[AnyRef](null) {}

    class GotoDashboard() extends ScreenData[AnyRef](null) {}

    class AllActivities() extends ScreenData[AnyRef](null) {}
}
