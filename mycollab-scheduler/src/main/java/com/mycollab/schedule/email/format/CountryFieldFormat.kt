package com.mycollab.schedule.email.format

import com.hp.gagawa.java.elements.Span
import com.mycollab.core.utils.StringUtils
import com.mycollab.schedule.email.MailContext
import org.apache.commons.beanutils.PropertyUtils
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class CountryFieldFormat(fieldName: String, displayName: Enum<*>) : FieldFormat(fieldName, displayName) {
    override fun formatField(context: MailContext<*>): String {
        val wrappedBean = context.wrappedBean
        try {
            val countryCode = PropertyUtils.getProperty(wrappedBean, fieldName) as String
            val locale = Locale("", countryCode)
            return Span().appendText(locale.getDisplayCountry(locale)).write()
        } catch (e: Exception) {
            return Span().write()
        }
    }

    override fun formatField(context: MailContext<*>, value: String): String {
        if (StringUtils.isBlank(value)) {
            return Span().write()
        }

        val locale = Locale("", value)
        return Span().appendText(locale.getDisplayCountry(context.locale)).write()
    }
}