package com.esofthead.mycollab.module.project.service.esb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectBugDeleteListenerImpl implements ProjectBugDeleteListener {

	private static Logger log = LoggerFactory
			.getLogger(ProjectBugDeleteListenerImpl.class);

	@Override
	public void bugRemoved(String username, int accountId, int projectId,
			int bugId) {
		log.debug("Remove bug {} of project {} by user {}", new Object[] {
				bugId, projectId, username });

		removeBugFiles(accountId, projectId, bugId);
		removeBugComments(bugId);
	}

	private void removeBugFiles(int accountId, int projectId, int bugId) {

	}

	private void removeBugComments(int bugId) {

	}

}
