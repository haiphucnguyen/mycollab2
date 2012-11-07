package com.esofthead.mycollab.module.tracker.dao;

import java.util.List;

import com.esofthead.mycollab.module.tracker.domain.Version;

public interface VersionMapperExt {
	List<Version> getAffectedVersionsByRelatedRefKey(String refKey);

	List<Version> getFixedVersionByRelatedRefKey(String refKey);
}
