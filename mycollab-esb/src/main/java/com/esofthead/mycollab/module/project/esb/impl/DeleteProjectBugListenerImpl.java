package com.esofthead.mycollab.module.project.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.esb.DeleteProjectBugListener;

@Component
public class DeleteProjectBugListenerImpl implements DeleteProjectBugListener {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectBugListenerImpl.class);

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
