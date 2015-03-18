package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.Problem
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object ProblemScreenData {

  class Read(params:Integer) extends ScreenData[Integer](params){}

  class Add(params:Problem) extends ScreenData[Problem](params){}

  class Edit(params:Problem) extends ScreenData[Problem](params){}

  class Search(params:ProblemSearchCriteria) extends ScreenData[ProblemSearchCriteria](params){}
}
