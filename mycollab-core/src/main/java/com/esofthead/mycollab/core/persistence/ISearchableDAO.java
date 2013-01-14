package com.esofthead.mycollab.core.persistence;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface ISearchableDAO<S extends SearchCriteria> {

    int getTotalCount(S criteria);

    /**
     *
     * @param criteria
     * @param skipNum
     * @param maxResult
     * @return
     */
    List findPagableListByCriteria(S criteria, RowBounds rowBounds);
    
    Integer getNextItemKey(S criteria);
    
    Integer getPreviousItemKey(S criteria);

    void removeByCriteria(S criteria);
}
