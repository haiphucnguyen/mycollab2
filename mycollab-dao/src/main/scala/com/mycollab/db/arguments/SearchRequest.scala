package com.mycollab.db.arguments

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
@SerialVersionUID(1L)
class SearchRequest(@BeanProperty var currentPage: Integer, @BeanProperty var numberOfItems: Integer) {
  @BeanProperty val requestedUser: String = GroupIdProvider.getRequestedUser
}

object SearchRequest {
  val DEFAULT_NUMBER_SEARCH_ITEMS = 25
}
