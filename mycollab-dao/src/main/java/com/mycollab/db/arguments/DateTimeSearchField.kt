package com.mycollab.db.arguments

import com.mycollab.core.utils.DateTimeUtils
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class DateTimeSearchField(operation: String, comparison: String, dateVal: Date, value: Date = DateTimeUtils.convertDateTimeToUTC(dateVal)) : SearchField(operation) {
    companion object {
        val LESS_THAN = "<"
        val LESS_THAN_EQUAL = "<="
        val GREATER_THAN = ">"
        val GREATER_THAN_EQUAL = ">="
        val EQUAL = "="
        val NOT_EQUAL = "<>"
    }
}