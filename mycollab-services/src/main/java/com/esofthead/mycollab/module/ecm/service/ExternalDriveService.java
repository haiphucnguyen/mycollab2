package com.esofthead.mycollab.module.ecm.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;

public interface ExternalDriveService extends
		ICrudService<Integer, ExternalDrive> {

	List<ExternalDrive> getExternalDrivesOfUser(String username);
}
