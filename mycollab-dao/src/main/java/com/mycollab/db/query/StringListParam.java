package com.mycollab.db.query;

import com.mycollab.common.i18n.QueryI18nEnum.CollectionI18nEnum;
import com.mycollab.db.arguments.CollectionValueSearchField;

import java.util.Collection;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class StringListParam extends ColumnParam {

    public static CollectionI18nEnum[] OPTIONS = {CollectionI18nEnum.IN, CollectionI18nEnum.NOT_IN};

    private List<String> values;

    public StringListParam(String id, String table, String column, List<String> values) {
        super(id, table, column);
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public CollectionValueSearchField buildStringParamInList(String oper, Collection<?> values) {
        String IN_EXPR = "%s.%s in ";
        return new CollectionValueSearchField(oper, String.format(IN_EXPR, this.getTable(), this.getColumn()), values);
    }

    public CollectionValueSearchField buildStringParamNotInList(String oper, Collection<?> values) {
        String NOT_IN_EXPR = "%s.%s not in ";
        return new CollectionValueSearchField(oper, String.format(NOT_IN_EXPR, this.getTable(), this.getColumn()), values);
    }
}