package com.mycollab.db.query;

import com.mycollab.db.arguments.CollectionValueSearchField;
import com.mycollab.db.arguments.SearchField;

import java.util.Collection;
import java.util.List;

import static com.mycollab.common.i18n.QueryI18nEnum.CollectionI18nEnum;

/**
 * @author MyCollab Ltd.
 * @since 4.3.0
 */
public class I18nStringListParam extends ColumnParam {

    public static CollectionI18nEnum[] OPTIONS = {CollectionI18nEnum.IN, CollectionI18nEnum.NOT_IN};

    private List<? extends Enum<?>> values;

    public I18nStringListParam(String id, String table, String column, List<? extends Enum<?>> values) {
        super(id, table, column);
        this.values = values;
    }

    public List<? extends Enum<?>> getValues() {
        return values;
    }

    public void setValues(List<? extends Enum<?>> lstValues) {
        this.values = lstValues;
    }

    public CollectionValueSearchField buildStringParamInList(String oper, Collection<?> value) {
        String IN_EXPR = "%s.%s in ";
        return new CollectionValueSearchField(oper, String.format(IN_EXPR, this.getTable(), this.getColumn()), value);
    }

    public CollectionValueSearchField andStringParamInList(Collection<?> value) {
        return buildStringParamInList(SearchField.AND, value);
    }

    public CollectionValueSearchField orStringParamInList(Collection<?> value) {
        return buildStringParamInList(SearchField.OR, value);
    }

    public CollectionValueSearchField buildStringParamNotInList(String oper, Collection<?> value) {
        String NOT_IN_EXPR = "%s.%s not in ";
        return new CollectionValueSearchField(oper, String.format(NOT_IN_EXPR,
                this.getTable(), this.getColumn()), value);
    }

    public CollectionValueSearchField andStringParamNotInList(List<?> value) {
        return buildStringParamNotInList(SearchField.AND, value);
    }

    public CollectionValueSearchField orStringParamNotInList(List<?> value) {
        return buildStringParamNotInList(SearchField.OR, value);
    }
}
