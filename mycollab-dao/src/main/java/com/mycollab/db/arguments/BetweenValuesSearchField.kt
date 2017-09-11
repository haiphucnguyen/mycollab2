package com.mycollab.db.arguments

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class BetweenValuesSearchField(operation: String, expression: String, value: Any, secondValue: Any, queryCount: String = expression, querySelect: String = expression): SearchField(operation) {

}