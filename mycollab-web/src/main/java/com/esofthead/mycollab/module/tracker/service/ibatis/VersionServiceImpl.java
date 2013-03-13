package com.esofthead.mycollab.module.tracker.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.tracker.dao.VersionMapper;
import com.esofthead.mycollab.module.tracker.dao.VersionMapperExt;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "versionname", type = ProjectContants.BUG_VERSION, extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.BUG_VERSION)
public class VersionServiceImpl extends
		DefaultService<Integer, Version, VersionSearchCriteria> implements
		VersionService {

	@Autowired
	private VersionMapper versionMapper;

	@Autowired
	private VersionMapperExt versionMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Version> getCrudMapper() {
		return versionMapper;
	}

	@Override
	public ISearchableDAO<VersionSearchCriteria> getSearchMapper() {
		return versionMapperExt;
	}

	@Override
	public SimpleVersion findVersionById(int versionId) {
		return versionMapperExt.findVersionById(versionId);
	}
}
