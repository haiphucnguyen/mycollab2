package com.mycollab.module.ecm.service;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.db.persistence.service.ICrudService;
import com.mycollab.module.ecm.domain.ExternalDrive;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@IgnoreCacheClass
public interface ExternalDriveService extends ICrudService<Integer, ExternalDrive> {

    List<ExternalDrive> getExternalDrivesOfUser(String username);
}
