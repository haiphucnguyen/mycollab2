package com.mycollab.schedule.email.format

import java.util.Locale

import com.hp.gagawa.java.elements.Span
import com.mycollab.core.utils.StringUtils
import com.mycollab.schedule.email.MailContext
import org.apache.commons.beanutils.PropertyUtils

/**
  * @author MyCollab Ltd
  * @since 5.4.2
  */
class CountryFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {
  override def formatField(context: MailContext[_]): String = {
    val wrappedBean = context.wrappedBean
    var countryCode: String = null
    try {
      countryCode = PropertyUtils.getProperty(wrappedBean, fieldName).asInstanceOf[String]
      if (countryCode == null) {
        new Span().write
      } else {
        val locale = new Locale("", countryCode)
        new Span().appendText(locale.getDisplayCountry(locale)).write
      }
    } catch {
      case e: Any => new Span().write
    }
  }
  
  override def formatField(context: MailContext[_], value: String): String = {
    if (StringUtils.isBlank(value)) {
      return new Span().write
    }
    
    val locale = new Locale("", value)
    new Span().appendText(locale.getDisplayCountry(context.locale)).write
  }
}
