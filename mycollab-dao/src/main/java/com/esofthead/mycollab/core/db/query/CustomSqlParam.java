package com.esofthead.mycollab.core.db.query;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NoValueSearchField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.0
 *
 */
public abstract class CustomSqlParam extends Param {
	@SuppressWarnings("rawtypes")
	public CustomSqlParam(String id, Enum displayName) {
		super(id, displayName);
	}

	public abstract NoValueSearchField buildPropertyParamInList(String oper,
			Collection<?> value);

	public abstract NoValueSearchField buildPropertyParamNotInList(String oper,
			Collection<?> value);
}
