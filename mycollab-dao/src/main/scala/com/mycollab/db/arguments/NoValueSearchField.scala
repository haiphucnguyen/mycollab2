package com.mycollab.db.arguments

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class NoValueSearchField(operation: String, expression: String) extends SearchField(operation) {
  @BeanProperty val queryCount = expression
  @BeanProperty val querySelect = expression
}
