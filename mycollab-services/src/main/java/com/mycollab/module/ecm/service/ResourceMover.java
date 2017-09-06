package com.mycollab.module.ecm.service;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.ecm.domain.Resource;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@IgnoreCacheClass
public interface ResourceMover extends IService {
    void moveResource(Resource scrRes, Resource descRes, String userMove, Integer sAccountId);
}
