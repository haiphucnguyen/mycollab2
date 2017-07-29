package com.mycollab.db.query;

import com.mycollab.db.arguments.NoValueSearchField;

import java.util.Collection;

/**
 * @author MyCollab Ltd.
 * @since 4.5.0
 */
public abstract class CustomSqlParam extends Param {
    public CustomSqlParam(String id) {
        super(id);
    }

    public abstract NoValueSearchField buildPropertyParamInList(String oper, Collection<?> value);

    public abstract NoValueSearchField buildPropertyParamNotInList(String oper, Collection<?> value);
}
