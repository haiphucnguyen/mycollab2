package com.esofthead.mycollab.schedule.email.format

import com.esofthead.mycollab.common.domain.Currency
import com.esofthead.mycollab.common.service.CurrencyService
import com.esofthead.mycollab.schedule.email.MailContext
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.hp.gagawa.java.elements.Span
import org.apache.commons.beanutils.PropertyUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
class CurrencyFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {
  private val LOG = LoggerFactory.getLogger(classOf[CurrencyFieldFormat])

  override def formatField(context: MailContext[_]): String = {
    val wrappedBean = context.wrappedBean
    var value: AnyRef = null
    try {
      value = PropertyUtils.getProperty(wrappedBean, fieldName)
      if (value == null) {
        new Span().write
      }
      else if (value.isInstanceOf[Currency]) {
        new Span().appendText((value.asInstanceOf[Currency]).getSymbol).write
      }
      else {
        val currencyService: CurrencyService = ApplicationContextUtil.getSpringBean(classOf[CurrencyService])
        val currency: Currency = currencyService.getCurrency(value.asInstanceOf[Integer])
        new Span().appendText(currency.getSymbol).write
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
      return new Span().write
    }

    try {
      val currencyService: CurrencyService = ApplicationContextUtil.getSpringBean(classOf[CurrencyService])
      val currencyId: Int = value.toInt
      val currency: Currency = currencyService.findByPrimaryKey(currencyId, context.getUser.getAccountId)
      if (currency != null) {
        currency.getFullname
      }
    }
    catch {
      case e: Exception =>
        LOG.error("Error", e)
    }

    value
  }
}
