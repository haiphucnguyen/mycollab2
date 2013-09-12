package com.esofthead.mycollab.common.service;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;

@RemotingDestination(channels = { "mycollab-amf", "mycollab-secure-amf" })
public interface ActivityStreamService extends
		IDefaultService<Integer, ActivityStream, ActivityStreamSearchCriteria> {

	Integer save(ActivityStream activityStream);
}
