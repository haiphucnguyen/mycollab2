package com.mycollab.db.arguments

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
open class NumberSearchField constructor(operation: String, var value: Int?, var compareOperator: String) : SearchField(operation) {
    constructor(value: Int?) : this(SearchField.AND, value, NumberSearchField.EQUAL)

    constructor(value: Int?, compareOperator: String) : this(SearchField.AND, value, compareOperator)

    companion object {
        @JvmField val EQUAL = "="
        @JvmField val NOT_EQUAL = "<>"
        @JvmField val LESS_THAN = "<"
        @JvmField val GREATER = ">"

        @JvmStatic fun equal(value: Int) = NumberSearchField(SearchField.AND, value, EQUAL)

        @JvmStatic fun greaterThan(value: Int) = NumberSearchField(SearchField.AND, value, GREATER)

        @JvmStatic fun lessThan(value: Int) = NumberSearchField(SearchField.AND, value, LESS_THAN)
    }
}