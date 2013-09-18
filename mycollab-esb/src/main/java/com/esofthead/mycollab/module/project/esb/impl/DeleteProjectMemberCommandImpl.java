package com.esofthead.mycollab.module.project.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.dao.ActivityStreamMapper;
import com.esofthead.mycollab.module.project.esb.DeleteProjectMemberCommand;
import com.esofthead.mycollab.module.user.service.UserService;

@Component
public class DeleteProjectMemberCommandImpl implements
		DeleteProjectMemberCommand {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectMemberCommandImpl.class);

	@Autowired
	private ActivityStreamMapper activityStreamMapper;

	@Autowired
	private UserService userService;

	@Override
	public void projectMemberRemoved(String username, Integer projectMemberId,
			Integer projectId, Integer accountId) {
		log.debug(
				"Remove project member has username {}, project member id {} and project id {}",
				new Object[] { username, projectMemberId, projectId });

	}

}
