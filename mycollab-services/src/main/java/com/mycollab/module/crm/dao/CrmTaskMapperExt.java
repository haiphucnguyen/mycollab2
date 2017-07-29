package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.crm.domain.SimpleCrmTask;
import com.mycollab.module.crm.domain.criteria.CrmTaskSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface CrmTaskMapperExt extends ISearchableDAO<CrmTaskSearchCriteria> {
    SimpleCrmTask findById(Integer taskId);
}
