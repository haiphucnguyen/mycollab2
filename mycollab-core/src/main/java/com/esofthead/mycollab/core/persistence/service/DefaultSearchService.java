package com.esofthead.mycollab.core.persistence.service;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public abstract class DefaultSearchService<S extends SearchCriteria> implements
        ISearchableService<S> {

    public abstract ISearchableDAO<S> getSearchMapper();

    @Override
    public int getTotalCount(S criteria) {
        return getSearchMapper().getTotalCount(criteria);
    }

    @Override
    public List findPagableListByCriteria(SearchRequest<S> searchRequest) {
        return getSearchMapper().findPagableListByCriteria(
                searchRequest.getSearchCriteria(),
                new RowBounds((searchRequest.getCurrentPage() - 1)
                * searchRequest.getNumberOfItems(), searchRequest
                .getNumberOfItems()));
    }

    @Override
    public void removeByCriteria(S criteria) {
        getSearchMapper().removeByCriteria(criteria);

    }
}
