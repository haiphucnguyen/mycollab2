package com.mycollab.db.arguments

import org.joda.time.LocalDate
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class DateSearchField(operation: String, dateVal: Date, comparison: String, value: Date = LocalDate(dateVal).toDate()) : SearchField(operation) {
    constructor(dateVal: Date) : this(SearchField.AND, dateVal, DateSearchField.LESS_THAN)

    constructor(dateVal: Date, comparison: String) : this(SearchField.AND, dateVal, comparison)

    companion object {
        val LESS_THAN = "<"
        val LESS_THAN_EQUAL = "<="
        val GREATER_THAN = ">"
        val GREATER_THAN_EQUAL = ">="
        val EQUAL = "="
        val NOT_EQUAL = "<>"
    }
}