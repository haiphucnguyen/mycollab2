package com.mycollab.db.arguments

import com.mycollab.core.utils.StringUtils

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class StringSearchField(operation: String, @BeanProperty val value: String) extends SearchField(operation) {
  def this() = this(SearchField.AND, "")
}

object StringSearchField {
  def and(value: String): StringSearchField = if (StringUtils.isNotBlank(value)) new StringSearchField(SearchField.AND, value) else null
}
