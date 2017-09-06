package com.mycollab.db.query;

import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.OneValueSearchField;
import com.mycollab.db.arguments.SearchField;

import static com.mycollab.common.i18n.QueryI18nEnum.StringI18nEnum;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class PropertyParam extends ColumnParam {

    public static StringI18nEnum[] OPTIONS = {StringI18nEnum.IS, StringI18nEnum.IS_NOT};

    private static final String IS_EXPR = "%s.%s = ";
    private static final String IS_NOT_EXPR = "%s.%s <> ";

    public PropertyParam(String id, String table, String column) {
        super(id, table, column);
    }

    public SearchField buildSearchField(String prefixOper, String compareOper, Object value) {
        StringI18nEnum compareValue = StringI18nEnum.valueOf(compareOper);
        switch (compareValue) {
            case IS:
                return buildPropertyIs(prefixOper, value);
            case IS_NOT:
                return buildPropertyIsNot(prefixOper, value);
            default:
                throw new MyCollabException("Not support");
        }
    }

    public OneValueSearchField buildPropertyIs(String oper, Object value) {
        return new OneValueSearchField(oper, String.format(IS_EXPR, table, column), value);
    }

    public OneValueSearchField buildPropertyIsNot(String oper, Object value) {
        return new OneValueSearchField(oper, String.format(IS_NOT_EXPR, table, column), value);
    }
}
