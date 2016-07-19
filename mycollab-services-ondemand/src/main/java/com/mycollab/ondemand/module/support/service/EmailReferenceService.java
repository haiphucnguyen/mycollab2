package com.mycollab.ondemand.module.support.service;

import com.mycollab.db.persistence.service.IService;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public interface EmailReferenceService extends IService {
    void save(String email);

    void remove(String email);
}
