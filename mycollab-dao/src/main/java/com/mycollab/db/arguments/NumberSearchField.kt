package com.mycollab.db.arguments

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
open class NumberSearchField @JvmOverloads constructor(operation: String, val value: Int?, compareOperator: String) : SearchField(operation) {
    constructor(value: Int?) : this(SearchField.AND, value, NumberSearchField.EQUAL)

    constructor(value: Int?, compareOperator: String) : this(SearchField.AND, value, compareOperator)

    companion object {
        val EQUAL = "="
        val NOT_EQUAL = "<>"
        val LESS_THAN = "<"
        val GREATER = ">"

        fun equal(value: Int) = NumberSearchField(SearchField.AND, value, EQUAL)

        fun greaterThan(value: Int) = NumberSearchField(SearchField.AND, value, GREATER)

        fun lessThan(value: Int) = NumberSearchField(SearchField.AND, value, LESS_THAN)
    }
}