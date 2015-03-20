package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.tracker.domain.Version
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object VersionScreenData {

  class Read(param: Integer) extends ScreenData[Integer](param) {}

  class Add(param: Version) extends ScreenData[Version](param) {}

  class Edit(param: Version) extends ScreenData[Version](param) {}

  class Search(param: VersionSearchCriteria) extends ScreenData[VersionSearchCriteria](param) {}

}
