package com.esofthead.mycollab.schedule.email.format

import java.util.Date

import com.esofthead.mycollab.configuration.LocaleHelper
import com.esofthead.mycollab.core.utils.{DateTimeUtils, TimezoneMapper}
import com.esofthead.mycollab.schedule.email.MailContext
import com.hp.gagawa.java.elements.Span
import org.apache.commons.beanutils.PropertyUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
class DateFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {
  private val LOG = LoggerFactory.getLogger(classOf[DateFieldFormat])

  override def formatField(context: MailContext[_]): String = {
    val wrappedBean = context.wrappedBean
    var value: AnyRef = null
    try {
      value = PropertyUtils.getProperty(wrappedBean, fieldName)
      if (value == null) {
        new Span().write
      }
      else {
        new Span().appendText(DateTimeUtils.formatDate(value.asInstanceOf[Date], LocaleHelper.getDateFormatAssociateToLocale(context.getLocale), TimezoneMapper.getTimezone(context.getUser.getTimezone))).write
      }
    }
    catch {
      case e: Any =>
        LOG.error("Can not generate email field: " + fieldName, e)
        new Span().write
    }
  }

  override def formatField(context: MailContext[_], value: String): String = {
    if (StringUtils.isBlank(value)) {
      new Span().write
    }

    DateTimeUtils.converToStringWithUserTimeZone(value, LocaleHelper.getDateFormatAssociateToLocale(context.getLocale), context.getTimeZone)
  }
}
