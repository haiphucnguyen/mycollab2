package com.mycollab.module.project.view.parameters

import com.mycollab.module.tracker.domain.BugWithBLOBs
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object BugScreenData {

    class Read(params: Integer) extends ScreenData[Integer](params) {}

    class Add(params: BugWithBLOBs) extends ScreenData[BugWithBLOBs](params) {}

    class Edit(params: BugWithBLOBs) extends ScreenData[BugWithBLOBs](params) {}

}
