package com.mycollab.db.query

import com.mycollab.core.MyCollabException
import com.mycollab.db.arguments.BetweenValuesSearchField
import com.mycollab.db.arguments.OneValueSearchField
import com.mycollab.db.arguments.SearchField

import java.lang.reflect.Array
import java.util.Date

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
class DateParam(id: String, table: String, column: String) : ColumnParam(id, table, column) {

    fun buildSearchField(prefixOper: String, compareOper: String, dateValue1: Date, dateValue2: Date): SearchField {
        when (compareOper) {
            DateParam.BETWEEN -> return buildDateValBetween(prefixOper, dateValue1, dateValue2)
            DateParam.NOT_BETWEEN -> return buildDateValNotBetween(prefixOper, dateValue1, dateValue2)
            else -> throw MyCollabException("Not support yet")
        }
    }

    fun buildSearchField(prefixOper: String, compareOper: String, dateValue: Date): SearchField {
        when (compareOper) {
            DateParam.IS -> return buildDateIsEqual(prefixOper, dateValue)
            DateParam.IS_NOT -> return buildDateIsNotEqual(prefixOper, dateValue)
            DateParam.BEFORE -> return buildDateIsLessThan(prefixOper, dateValue)
            DateParam.AFTER -> return buildDateIsGreaterThan(prefixOper, dateValue)
            else -> throw MyCollabException("Not support yet")
        }
    }

    private fun buildDateValBetween(oper: String, value1: Date, value2: Date): BetweenValuesSearchField {
        return BetweenValuesSearchField(oper, String.format("DATE(%s.%s) BETWEEN", table, column), value1, value2)
    }

    private fun buildDateValNotBetween(oper: String, value1: Date, value2: Date): BetweenValuesSearchField {
        return BetweenValuesSearchField(oper, String.format("DATE(%s.%s) NOT BETWEEN", table, column), value1, value2)
    }

    private fun buildDateIsEqual(oper: String, value: Date): OneValueSearchField {
        return OneValueSearchField(oper, String.format("DATE(%s.%s) = ", table, column), value)
    }

    private fun buildDateIsNotEqual(oper: String, value: Date): OneValueSearchField {
        return OneValueSearchField(oper, String.format("DATE(%s.%s) <> ", table, column), value)
    }

    private fun buildDateIsGreaterThan(oper: String, value: Date): OneValueSearchField {
        return OneValueSearchField(oper, String.format("DATE(%s.%s) >= ", table, column), value)
    }

    private fun buildDateIsLessThan(oper: String, value: Date): OneValueSearchField {
        return OneValueSearchField(oper, String.format("DATE(%s.%s) <= ", table, column), value)
    }

    companion object {
        val IS = "is"
        val IS_NOT = "isn't"
        val BEFORE = "is before"
        val AFTER = "is after"
        val BETWEEN = "between"
        val NOT_BETWEEN = "not between"

        var OPTIONS = arrayOf(IS, IS_NOT, BEFORE, AFTER, BETWEEN, NOT_BETWEEN)

        fun inRangeDate(dateParam: DateParam, variableInjector: VariableInjector<*>): SearchField? {
            val value = variableInjector.eval()
            return if (value != null) {
                if (value.javaClass.isArray) {
                    dateParam.buildSearchField(SearchField.AND, BETWEEN, Array.get(value, 0) as Date, Array.get(value, 1) as Date)
                } else {
                    dateParam.buildSearchField(SearchField.AND, BETWEEN, value as Date)
                }
            } else {
                null
            }
        }
    }
}
