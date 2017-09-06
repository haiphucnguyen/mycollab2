package com.mycollab.vaadin.mvp;

import com.mycollab.db.arguments.SearchCriteria;

/**
 * @param <S>
 * @author MyCollab
 * @since 1.0
 */
public interface ListCommand<S extends SearchCriteria> {

    void doSearch(S searchCriteria);
}
