package com.esofthead.mycollab.ondemand.module.ecm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.ecm.dao.ExternalDriveMapper;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalDriveExample;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;

@Service
public class ExternalDriveServiceImpl extends DefaultCrudService<Integer, ExternalDrive> implements ExternalDriveService {

	@Autowired
	private ExternalDriveMapper externalDriveMapper;

	@Override
	public ICrudGenericDAO<Integer, ExternalDrive> getCrudMapper() {
		return externalDriveMapper;
	}

	@Override
	public List<ExternalDrive> getExternalDrivesOfUser(String username) {
		ExternalDriveExample ex = new ExternalDriveExample();
		ex.createCriteria().andOwnerEqualTo(username);
		return externalDriveMapper.selectByExample(ex);
	}
}
