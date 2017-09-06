package com.mycollab.db.query;

import com.mycollab.db.arguments.CollectionValueSearchField;

import java.util.Collection;

import static com.mycollab.common.i18n.QueryI18nEnum.CollectionI18nEnum;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class PropertyListParam<P> extends ColumnParam {

    public static final CollectionI18nEnum[] OPTIONS = {CollectionI18nEnum.IN, CollectionI18nEnum.NOT_IN};

    public PropertyListParam(String id, String table, String column) {
        super(id, table, column);
    }

    public CollectionValueSearchField buildPropertyParamInList(String oper, Collection<P> value) {
        String IN_EXPR = "%s.%s in ";
        return new CollectionValueSearchField(oper, String.format(IN_EXPR, this.getTable(), this.getColumn()), value);
    }

    public CollectionValueSearchField buildPropertyParamNotInList(String oper, Collection<P> value) {
        String NOT_IN_EXPR = "%s.%s not in ";
        return new CollectionValueSearchField(oper, String.format(NOT_IN_EXPR, this.getTable(), this.getColumn()), value);
    }
}
