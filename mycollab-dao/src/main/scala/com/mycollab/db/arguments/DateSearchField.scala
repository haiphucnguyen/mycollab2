package com.mycollab.db.arguments

import java.util.Date

import org.joda.time.LocalDate

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class DateSearchField(operation: String, dateVal: Date,
                      @BeanProperty val comparison: String) extends SearchField(operation) {
  @BeanProperty val value = new LocalDate(dateVal).toDate
  
  def this(dateVal: Date) = this(SearchField.AND, dateVal, DateSearchField.LESS_THAN)
  
  def this(dateVal: Date, comparison: String) = this(SearchField.AND, dateVal, comparison)
}

object DateSearchField {
  val LESS_THAN = "<"
  val LESS_THAN_EQUAL = "<="
  val GREATER_THAN = ">"
  val GREATER_THAN_EQUAL = ">="
  val EQUAL = "="
  val NOT_EQUAL = "<>"
}
