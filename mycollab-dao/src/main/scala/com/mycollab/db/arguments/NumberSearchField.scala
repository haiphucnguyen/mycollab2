package com.mycollab.db.arguments

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class NumberSearchField(operation: String, @BeanProperty val value: Number,
                        @BeanProperty val compareOperator: String) extends SearchField(operation) {
  def this(value: Number) = this(SearchField.AND, value, NumberSearchField.EQUAL)
  
  def this(value: Number, compareOperator: String) = this(SearchField.AND, value, compareOperator)
}

object NumberSearchField {
  val EQUAL = "="
  val NOT_EQUAL = "<>"
  val LESS_THAN = "<"
  val GREATER = ">"
  
  def equal(value: Number): NumberSearchField = new NumberSearchField(SearchField.AND, value, EQUAL)
  
  def greaterThan(value: Number): NumberSearchField = new NumberSearchField(SearchField.AND, value, GREATER)
  
  def lessThan(value: Number): NumberSearchField = new NumberSearchField(SearchField.AND, value, LESS_THAN)
}
