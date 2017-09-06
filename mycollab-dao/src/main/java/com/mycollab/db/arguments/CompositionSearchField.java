package com.mycollab.db.arguments;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class CompositionSearchField extends SearchField {
    private static final long serialVersionUID = 1L;

    private List<SearchField> fields;

    public CompositionSearchField(String oper) {
        this.operation = oper;
    }

    public List<SearchField> getFields() {
        return fields;
    }

    public void setFields(List<SearchField> fields) {
        this.fields = fields;
    }

    public void addField(SearchField field) {
        if (fields == null) {
            fields = new ArrayList<>();
        }

        fields.add(field);
    }
}
