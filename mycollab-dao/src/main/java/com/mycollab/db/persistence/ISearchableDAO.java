package com.mycollab.db.persistence;

import com.mycollab.db.arguments.SearchCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @param <S>
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ISearchableDAO<S extends SearchCriteria> {

    /**
     * @param criteria
     * @return
     */
    int getTotalCount(@Param("searchCriteria") S criteria);

    /**
     * @param criteria
     * @param rowBounds
     * @return
     */
    List findPageableListByCriteria(@Param("searchCriteria") S criteria, RowBounds rowBounds);

    /**
     * @param criteria
     * @return
     */
    Integer getNextItemKey(@Param("searchCriteria") S criteria);

    /**
     * @param criteria
     * @return
     */
    Integer getPreviousItemKey(@Param("searchCriteria") S criteria);

    /**
     * @param criteria
     */
    void removeByCriteria(@Param("searchCriteria") S criteria);
}
