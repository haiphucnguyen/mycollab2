package com.esofthead.mycollab.core.persistence;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author MyCollab Ltd.
 * @since 1.0
 *
 * @param <R>
 * @param <S>
 */
public interface IMassUpdateDAO<R, S extends SearchCriteria> {
    void updateBySearchCriteria(@Param("record") R record, @Param("searchCriteria") S searchCriteria);
}
