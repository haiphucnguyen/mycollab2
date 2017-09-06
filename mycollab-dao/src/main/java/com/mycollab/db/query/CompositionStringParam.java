package com.mycollab.db.query;

import com.mycollab.db.arguments.CompositionSearchField;
import com.mycollab.db.arguments.SearchField;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class CompositionStringParam extends Param {
    private StringParam[] params;

    public CompositionStringParam(String id, StringParam... params) {
        this.id = id;
        this.params = params;
    }

    public StringParam[] getParams() {
        return params;
    }

    public void setParams(StringParam[] params) {
        this.params = params;
    }

    public SearchField buildSearchField(String prefixOper, String compareOper, String value) {
        CompositionSearchField searchField = new CompositionSearchField(prefixOper);
        for (StringParam param : params) {
            SearchField field = param.buildSearchField("", compareOper, value);
            searchField.addField(field);
        }
        return searchField;
    }
}
