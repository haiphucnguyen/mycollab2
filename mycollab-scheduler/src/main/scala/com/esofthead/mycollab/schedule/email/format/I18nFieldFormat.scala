package com.esofthead.mycollab.schedule.email.format

import com.esofthead.mycollab.core.utils.BeanUtility
import com.esofthead.mycollab.i18n.LocalizationHelper
import com.esofthead.mycollab.schedule.email.MailContext
import com.hp.gagawa.java.elements.Span
import org.apache.commons.beanutils.PropertyUtils
import org.slf4j.{Logger, LoggerFactory}

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
class I18nFieldFormat(fieldName: String, displayName: Enum[_], enumKey: Class[_<: Enum[_]])
  extends FieldFormat(fieldName, displayName) {

  private val LOG: Logger = LoggerFactory.getLogger(classOf[I18nFieldFormat])

  override def formatField(context: MailContext[_]): String = {
    val wrappedBean = context.wrappedBean
    var value: AnyRef = null
    try {
      value = PropertyUtils.getProperty(wrappedBean, fieldName)
      if (value == null) {
        new Span().write
      }
      else {
        val valueEnum = Enum.valueOf(classOf[Enum[_]], value.toString)
        new Span().appendText(LocalizationHelper.getMessage(context.getLocale, valueEnum)).write
      }
    }
    catch {
      case e: Any =>
        LOG.error("Can not generate of object " + BeanUtility.printBeanObj(wrappedBean) + " field: " + fieldName + " and " + value, e)
        new Span().write
    }
  }

  override def formatField(context: MailContext[_], value: String): String = {
    try {
      val valueEnum: Enum[_] = Enum.valueOf(enumKey, value.toString)
      LocalizationHelper.getMessage(context.getLocale, valueEnum)
    }
    catch {
      case e: Exception =>
        LOG.error("Can not generate of object field: " + fieldName + " and " + value, e)
        new Span().write
    }
  }
}
