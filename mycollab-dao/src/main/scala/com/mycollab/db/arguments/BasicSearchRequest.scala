package com.mycollab.db.arguments

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class BasicSearchRequest[S <: SearchCriteria](@BeanProperty val searchCriteria: S = null,
                                              currentPage: Integer, numberOfItems: Integer)
  extends SearchRequest(currentPage, numberOfItems) {
  def this(searchCriteria: S) = this(searchCriteria, 1, Integer.MAX_VALUE)
}
