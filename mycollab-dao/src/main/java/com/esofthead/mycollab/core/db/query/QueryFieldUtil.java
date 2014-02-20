package com.esofthead.mycollab.core.db.query;

import java.util.List;

import com.esofthead.mycollab.core.arguments.ListValueSearchField;
import com.esofthead.mycollab.core.arguments.NoValueSearchField;
import com.esofthead.mycollab.core.arguments.OneValueSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class QueryFieldUtil {
	private static String NULL_EXPR = "%s.%s is null";
	private static String NOT_NULL_EXPR = "%s.%s is not null";
	private static String EQUAL_EXPR = "%s.%s = ";
	private static String NOT_EQUAL_EXPR = "%s.%s <> ";
	private static String LIKE_EXPR = "%s.%s like ";
	private static String NOT_LIKE_EXPR = "%s.%s not like ";
	private static String IN_EXPR = "%s.%s in ";
	private static String NOT_IN_EXPR = "%s.%s not in ";
	public static String CONTAINS_EXPR = "";

	public static NoValueSearchField buildStringParamIsNull(String oper,
			StringParam param) {
		return new NoValueSearchField(oper, String.format(NULL_EXPR,
				param.getTable(), param.getColumn()));
	}

	public static NoValueSearchField andStringParamIsNull(StringParam param) {
		return buildStringParamIsNull(SearchField.AND, param);
	}

	public static NoValueSearchField orStringParamIsNull(StringParam param) {
		return buildStringParamIsNull(SearchField.OR, param);
	}

	public static NoValueSearchField buildStringParamIsNotNull(String oper,
			StringParam param) {
		return new NoValueSearchField(oper, String.format(NOT_NULL_EXPR,
				param.getTable(), param.getColumn()));
	}

	public static NoValueSearchField andStringParamIsNotNull(StringParam param) {
		return buildStringParamIsNotNull(SearchField.AND, param);
	}

	public static NoValueSearchField orStringParamIsNotNull(StringParam param) {
		return buildStringParamIsNull(SearchField.OR, param);
	}

	public static OneValueSearchField buildStringParamIsEqual(String oper,
			StringParam param, Object value) {
		return new OneValueSearchField(oper, String.format(EQUAL_EXPR,
				param.getTable(), param.getColumn()), value);
	}

	public static OneValueSearchField andStringParamIsEqual(StringParam param,
			Object value) {
		return buildStringParamIsEqual(SearchField.AND, param, value);
	}

	public static OneValueSearchField orStringParamIsEqual(StringParam param,
			Object value) {
		return buildStringParamIsEqual(SearchField.OR, param, value);
	}

	public static OneValueSearchField buildStringParamIsNotEqual(String oper,
			StringParam param, Object value) {
		return new OneValueSearchField(oper, String.format(NOT_EQUAL_EXPR,
				param.getTable(), param.getColumn()), value);
	}

	public static OneValueSearchField andStringParamIsNotEqual(
			StringParam param, Object value) {
		return buildStringParamIsNotEqual(SearchField.AND, param, value);
	}

	public static OneValueSearchField orStringParamIsNotEqual(
			StringParam param, Object value) {
		return buildStringParamIsNotEqual(SearchField.OR, param, value);
	}

	public static OneValueSearchField andStringParamIsLike(StringParam param,
			Object value) {
		return new OneValueSearchField(SearchField.AND, String.format(
				LIKE_EXPR, param.getTable(), param.getColumn()), value);
	}

	public static OneValueSearchField orStringParamIsLike(StringParam param,
			Object value) {
		return new OneValueSearchField(SearchField.OR, String.format(LIKE_EXPR,
				param.getTable(), param.getColumn()), value);
	}

	public static OneValueSearchField andStringParamIsNotLike(
			StringParam param, Object value) {
		return new OneValueSearchField(SearchField.AND, String.format(
				NOT_LIKE_EXPR, param.getTable(), param.getColumn()), value);
	}

	public static OneValueSearchField orStringParamIsNotLike(StringParam param,
			Object value) {
		return new OneValueSearchField(SearchField.OR, String.format(
				NOT_LIKE_EXPR, param.getTable(), param.getColumn()), value);
	}

	public static ListValueSearchField andStringParamInList(StringParam param,
			List<?> value) {
		return new ListValueSearchField(SearchField.AND, String.format(IN_EXPR,
				param.getTable(), param.getColumn()), value);
	}

	public static ListValueSearchField orStringParamInList(StringParam param,
			List<?> value) {
		return new ListValueSearchField(SearchField.OR, String.format(IN_EXPR,
				param.getTable(), param.getColumn()), value);
	}

	public static ListValueSearchField andStringParamNotInList(
			StringParam param, List<?> value) {
		return new ListValueSearchField(SearchField.AND, String.format(
				NOT_IN_EXPR, param.getTable(), param.getColumn()), value);
	}

	public static ListValueSearchField orStringParamNotInList(
			StringParam param, List<?> value) {
		return new ListValueSearchField(SearchField.OR, String.format(
				NOT_IN_EXPR, param.getTable(), param.getColumn()), value);
	}
}
