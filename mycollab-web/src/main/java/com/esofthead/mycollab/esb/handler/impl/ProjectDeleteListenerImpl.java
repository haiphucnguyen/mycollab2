package com.esofthead.mycollab.esb.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.esb.handler.ProjectDeleteListener;

@Component
public class ProjectDeleteListenerImpl implements ProjectDeleteListener {

	private static Logger log = LoggerFactory
			.getLogger(ProjectDeleteListenerImpl.class);

	@Override
	public void projectRemoved(int projectId) {
		log.debug("Remove project {}", projectId);

	}

}
