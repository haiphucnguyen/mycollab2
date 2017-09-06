package com.mycollab.module.crm.service;

import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.crm.domain.SimpleTarget;
import com.mycollab.module.crm.domain.Target;
import com.mycollab.module.crm.domain.criteria.TargetSearchCriteria;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface TargetService extends IDefaultService<Integer, Target, TargetSearchCriteria> {
    SimpleTarget findTargetById(int targetId);
}
