package com.esofthead.mycollab.core.db.query;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class AnyStringParam extends Param {
	public static final String IS = "is";
	public static final String IS_NOT = "isn't";
	public static final String CONTAINS = "contains";
	public static final String NOT_CONTAINS = "doesn't contains";
	public static final String IS_EMPTY = "is empty";
	public static final String IS_NOT_EMPTY = "is not empty";

	private static String NULL_EXPR = "%s.%s is null";
	private static String NOT_NULL_EXPR = "%s.%s is not null";
	private static String EQUAL_EXPR = "%s.%s = ";
	private static String NOT_EQUAL_EXPR = "%s.%s <> ";
	private static String LIKE_EXPR = "%s.%s like ";
	private static String NOT_LIKE_EXPR = "%s.%s not like ";

	public static String[] OPTIONS = { IS, IS_NOT, CONTAINS, NOT_CONTAINS,
			IS_EMPTY, IS_NOT_EMPTY };

	private String table;
	private String[] columns;

	public AnyStringParam(String id, String displayName, String table,
			String[] column) {
		super(id, displayName);
		this.table = table;
		this.columns = columns;
	}
}
