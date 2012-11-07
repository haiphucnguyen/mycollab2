package com.esofthead.mycollab.module.tracker.service;

import java.util.List;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.module.tracker.domain.Version;

public interface VersionService extends ICrudService<Integer, Version> {
	List<Version> getVersionsOfProject(int projectid);
}
