package com.esofthead.mycollab.core.db.query;

import com.esofthead.mycollab.core.arguments.NoValueSearchField;
import com.esofthead.mycollab.core.arguments.OneValueSearchField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class NumberParam extends ColumnParam {
	public static final String EQUAL = "=";
	public static final String NOT_EQUAL = "<>";
	public static final String LESS_THAN = "<";
	public static final String LESS_THAN_EQUAL = "<=";
	public static final String GREATER_THAN = ">";
	public static final String GREATER_THAN_EQUAL = "=>";
	public static final String IS_EMPTY = "is empty";
	public static final String IS_NOT_EMPTY = "is not empty";

	public static String[] OPTIONS = { EQUAL, NOT_EQUAL, LESS_THAN,
			LESS_THAN_EQUAL, GREATER_THAN, GREATER_THAN_EQUAL, IS_EMPTY,
			IS_NOT_EMPTY };

	private static String EQUAL_EXPR = "%s.%s = ";
	private static String NOT_EQUAL_EXPR = "%s.%s <> ";
	private static String NULL_EXPR = "%s.%s is null";
	private static String NOT_NULL_EXPR = "%s.%s is not null";
	private static String GREATER_THAN_EXPR = "%s.%s > ";
	private static String GREATER_THAN_EQUAL_EXPR = "%s.%s >= ";
	private static String LESS_THAN_EXPR = "%s.%s < ";
	private static String LESS_THAN_EQUAL_EXPR = "%s.%s <= ";

	public NumberParam(String id, String displayName, String table,
			String column) {
		super(id, displayName, table, column);
	}

	public OneValueSearchField buildParamIsEqual(String oper, Object value) {
		return new OneValueSearchField(oper, String.format(EQUAL_EXPR,
				this.getTable(), this.getColumn()), value);
	}

	public OneValueSearchField buildParamIsNotEqual(String oper, Object value) {
		return new OneValueSearchField(oper, String.format(NOT_EQUAL_EXPR,
				this.getTable(), this.getColumn()), value);
	}

	public NoValueSearchField buildParamIsNull(String oper) {
		return new NoValueSearchField(oper, String.format(NULL_EXPR,
				this.getTable(), this.getColumn()));
	}

	public NoValueSearchField buildParamIsNotNull(String oper) {
		return new NoValueSearchField(oper, String.format(NOT_NULL_EXPR,
				this.getTable(), this.getColumn()));
	}

	public OneValueSearchField buildParamIsGreaterThan(String oper, Object value) {
		return new OneValueSearchField(oper, String.format(GREATER_THAN_EXPR,
				this.getTable(), this.getColumn()), value);
	}

	public OneValueSearchField buildParamIsGreaterThanEqual(String oper,
			Object value) {
		return new OneValueSearchField(oper, String.format(
				GREATER_THAN_EQUAL_EXPR, this.getTable(), this.getColumn()),
				value);
	}

	public OneValueSearchField buildParamIsLessThan(String oper, Object value) {
		return new OneValueSearchField(oper, String.format(LESS_THAN_EXPR,
				this.getTable(), this.getColumn()), value);
	}

	public OneValueSearchField buildParamIsLessThanEqual(String oper,
			Object value) {
		return new OneValueSearchField(oper, String.format(
				LESS_THAN_EQUAL_EXPR, this.getTable(), this.getColumn()), value);
	}
}
