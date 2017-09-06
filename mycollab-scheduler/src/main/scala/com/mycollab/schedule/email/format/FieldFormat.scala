package com.mycollab.schedule.email.format

import com.mycollab.schedule.email.MailContext

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
abstract class FieldFormat(@BeanProperty var fieldName: String, @BeanProperty var displayName: Enum[_],
                           @BeanProperty var isColSpan: Boolean) {
  def this(fieldName: String, displayName: Enum[_]) = this(fieldName, displayName, false)
  
  /**
    *
    * @param context
    * @return
    */
  def formatField(context: MailContext[_]): String
  
  /**
    *
    * @param context
    * @param value
    * @return
    */
  def formatField(context: MailContext[_], value: String): String

  object Type extends Enumeration {
    type Type = Value
    val DEFAULT, DATE, DATE_TIME, CURRENCY = Value
  }
}
