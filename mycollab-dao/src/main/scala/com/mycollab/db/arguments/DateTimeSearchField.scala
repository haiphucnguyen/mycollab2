package com.mycollab.db.arguments

import java.util.Date

import com.mycollab.core.utils.DateTimeUtils

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class DateTimeSearchField(operation: String, @BeanProperty val comparison: String, dateVal: Date) extends SearchField(operation) {
  @BeanProperty val value = DateTimeUtils.convertDateTimeToUTC(dateVal)
}

object DateTimeSearchField {
  val LESS_THAN = "<"
  val LESS_THAN_EQUAL = "<="
  val GREATER_THAN = ">"
  val GREATER_THAN_EQUAL = ">="
  val EQUAL = "="
  val NOT_EQUAL = "<>"
}
