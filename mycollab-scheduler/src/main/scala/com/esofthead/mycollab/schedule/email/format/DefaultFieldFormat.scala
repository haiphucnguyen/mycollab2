package com.esofthead.mycollab.schedule.email.format

import com.esofthead.mycollab.schedule.email.MailContext
import com.hp.gagawa.java.elements.Span
import org.apache.commons.beanutils.PropertyUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.{Logger, LoggerFactory}

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
class DefaultFieldFormat(fieldName: String, displayName: Enum[_], isColSpan: Boolean) extends FieldFormat(fieldName,
  displayName, isColSpan) {
  private val LOG: Logger = LoggerFactory.getLogger(classOf[DefaultFieldFormat])

  def this(fieldName: String, displayName: Enum[_]) = this(fieldName, displayName, false)

  override def formatField(context: MailContext[_]): String = {
    val wrappedBean = context.wrappedBean

    try {
      val value = PropertyUtils.getProperty(wrappedBean, fieldName)
      if (value == null) {
        new Span().write
      }
      else {
        new Span().appendText(value.toString).write
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

    value
  }
}
