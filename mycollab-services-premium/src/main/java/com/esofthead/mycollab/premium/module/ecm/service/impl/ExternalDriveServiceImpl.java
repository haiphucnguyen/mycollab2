package com.esofthead.mycollab.premium.module.ecm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.ecm.dao.ExternalDriveMapper;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;

@Service
public class ExternalDriveServiceImpl extends
		DefaultCrudService<Integer, ExternalDrive> implements
		ExternalDriveService {

	@Autowired
	private ExternalDriveMapper externalDriveMapper;

	@SuppressWarnings("unchecked")
	@Override
	public ICrudGenericDAO<Integer, ExternalDrive> getCrudMapper() {
		return externalDriveMapper;
	}

	@Override
	public List<ExternalDrive> getExternalDrivesOfUser(String username) {
		return new ArrayList<>();
	}
}
