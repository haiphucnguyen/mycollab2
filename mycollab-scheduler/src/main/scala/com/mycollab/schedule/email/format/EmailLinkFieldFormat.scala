package com.mycollab.schedule.email.format

import com.hp.gagawa.java.elements.{A, Span}
import com.mycollab.schedule.email.MailContext
import org.apache.commons.beanutils.PropertyUtils
import org.slf4j.LoggerFactory

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
class EmailLinkFieldFormat(fieldName: String, displayName: Enum[_]) extends FieldFormat(fieldName, displayName) {
    val LOG = LoggerFactory.getLogger(classOf[EmailLinkFieldFormat])

    override def formatField(context: MailContext[_]): String = {
        val wrappedBean = context.wrappedBean
        try {
            val value = PropertyUtils.getProperty(wrappedBean, fieldName)
            formatEmail(value.asInstanceOf[String])
        } catch {
            case e: Any =>
                LOG.error("Error", e)
                new Span().write
        }
    }

    override def formatField(context: MailContext[_], value: String): String = formatEmail(value)

    private def formatEmail(value: String): String = {
        if (value == null) {
            new Span().write
        } else {
            val link: A = new A
            link.setStyle("text-decoration: none; color: rgb(36, 127, 211);")
            link.setHref("mailto:" + value.toString)
            link.appendText(value.toString)
            new Span().appendChild(link).write
        }
    }
}
