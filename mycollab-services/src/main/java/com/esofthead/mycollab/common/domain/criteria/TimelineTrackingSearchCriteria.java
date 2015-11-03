package com.esofthead.mycollab.common.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class TimelineTrackingSearchCriteria extends SearchCriteria {
    private StringSearchField type;
    private StringSearchField fieldgroup;
    private SetSearchField<Integer> extraTypeIds;

    public StringSearchField getType() {
        return type;
    }

    public void setType(StringSearchField type) {
        this.type = type;
    }

    public SetSearchField<Integer> getExtraTypeIds() {
        return extraTypeIds;
    }

    public void setExtraTypeIds(SetSearchField<Integer> extraTypeIds) {
        this.extraTypeIds = extraTypeIds;
    }

    public StringSearchField getFieldgroup() {
        return fieldgroup;
    }

    public void setFieldgroup(StringSearchField fieldgroup) {
        this.fieldgroup = fieldgroup;
    }
}
