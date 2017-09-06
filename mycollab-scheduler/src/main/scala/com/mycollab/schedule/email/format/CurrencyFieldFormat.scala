package com.mycollab.schedule.email.format

import com.hp.gagawa.java.elements.Span
import com.mycollab.core.utils.{CurrencyUtils, StringUtils}
import com.mycollab.schedule.email.MailContext
import org.apache.commons.beanutils.PropertyUtils
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
            else {
                new Span().appendText(value.asInstanceOf[String]).write
            }
        } catch {
            case e: Any =>
                LOG.error("Can not generate email field: " + fieldName, e)
                new Span().write
        }
    }

    override def formatField(context: MailContext[_], value: String): String = {
        if (StringUtils.isBlank(value)) {
            return new Span().write
        }

        CurrencyUtils.getInstance(value).getDisplayName
    }
}
