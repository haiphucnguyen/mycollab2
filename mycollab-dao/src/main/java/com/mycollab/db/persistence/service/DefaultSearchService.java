package com.mycollab.db.persistence.service;

import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.persistence.ISearchableDAO;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @param <S>
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class DefaultSearchService<S extends SearchCriteria> implements ISearchableService<S> {

    public abstract ISearchableDAO<S> getSearchMapper();

    @Override
    public Integer getTotalCount(S criteria) {
        return getSearchMapper().getTotalCount(criteria);
    }

    @Override
    public List findPageableListByCriteria(BasicSearchRequest<S> searchRequest) {
        return getSearchMapper().findPageableListByCriteria(searchRequest.getSearchCriteria(),
                new RowBounds((searchRequest.getCurrentPage() - 1) * searchRequest.getNumberOfItems(),
                        searchRequest.getNumberOfItems()));
    }

    @Override
    public List findAbsoluteListByCriteria(S searchCriteria, Integer firstIndex, Integer numberOftems) {
        return getSearchMapper().findPageableListByCriteria(searchCriteria,
                new RowBounds(firstIndex, numberOftems));
    }

    @Override
    public void removeByCriteria(S criteria, Integer accountId) {
        getSearchMapper().removeByCriteria(criteria);

    }

    @Override
    public Integer getNextItemKey(S criteria) {
        return getSearchMapper().getNextItemKey(criteria);
    }

    @Override
    public Integer getPreviousItemKey(S criteria) {
        return getSearchMapper().getPreviousItemKey(criteria);
    }
}
