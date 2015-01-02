package com.esofthead.mycollab.schedule.email.format

import com.esofthead.mycollab.schedule.email.MailContext

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
abstract class FieldFormat(val fieldName: String, val displayName: Enum[_], val isColSpan: Boolean) {
  def this(fieldName: String, displayName: Enum[_]) = this(fieldName, displayName, false)

  def formatField(context: MailContext[_]): String

  def formatField(context: MailContext[_], value: String): String

  object Type extends Enumeration {
    type Type = Value
    val DEFAULT, DATE, DATE_TIME, CURRENCY = Value
  }

}
