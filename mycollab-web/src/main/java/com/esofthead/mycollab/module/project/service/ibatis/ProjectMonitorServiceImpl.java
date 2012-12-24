package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.project.dao.ProjectMonitorMapper;
import com.esofthead.mycollab.module.project.domain.ProjectMonitor;
import com.esofthead.mycollab.module.project.service.ProjectMonitorService;

@Service
public class ProjectMonitorServiceImpl extends
		DefaultCrudService<Integer, ProjectMonitor> implements
		ProjectMonitorService {

	@Autowired
	private ProjectMonitorMapper projectMonitorMapper;
	
	@Override
	public ICrudGenericDAO<Integer, ProjectMonitor> getCrudMapper() {
		return projectMonitorMapper;
	}

}
