package com.mycollab.db.query;

import com.mycollab.db.arguments.NoValueSearchField;
import com.mycollab.db.arguments.SearchCriteria;

import java.util.Collection;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public abstract class SearchCriteriaBridgeParam<S extends SearchCriteria> extends Param {
    public SearchCriteriaBridgeParam(String id) {
        super(id);
    }

    public abstract S injectCriteriaInList(S searchCriteria, String oper, Collection<?> value);

    public abstract S injectCriteriaNotInList(S searchCriteria, String oper, Collection<?> value);
}
