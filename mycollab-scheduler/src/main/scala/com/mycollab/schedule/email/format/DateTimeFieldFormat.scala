package com.mycollab.schedule.email.format

import java.util.Date

import com.hp.gagawa.java.elements.Span
import com.mycollab.core.utils.{DateTimeUtils, StringUtils, TimezoneVal}
import com.mycollab.schedule.email.MailContext
import org.apache.commons.beanutils.PropertyUtils
import org.slf4j.LoggerFactory

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
class DateTimeFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {
  private val LOG = LoggerFactory.getLogger(classOf[DateTimeFieldFormat])

  override def formatField(context: MailContext[_]): String = {
    val wrappedBean = context.wrappedBean
    try {
      val value = PropertyUtils.getProperty(wrappedBean, fieldName)
      if (value == null) {
        new Span().write
      } else {
        new Span().appendText(DateTimeUtils.formatDate(value.asInstanceOf[Date], context.user.getDateFormat,
          context.locale, TimezoneVal.valueOf(context.user.getTimezone))).write
      }
    } catch {
      case e: Any =>
        LOG.error("Can not generate email field: " + fieldName, e)
        new Span().write
    }
  }

  override def formatField(context: MailContext[_], value: String): String = {
    if (StringUtils.isBlank(value)) {
      new Span().write
    }

    DateTimeUtils.convertToStringWithUserTimeZone(value, context.user.getDateFormat, context.locale, context.getTimeZone)
  }
}
